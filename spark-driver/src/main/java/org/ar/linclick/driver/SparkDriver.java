package org.ar.linclick.driver;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.distributed.entity.ClientInfo;
import org.ar.linclick.driver.entity.ClientInfoResult;
import org.ar.linclick.distributed.function.filters.FilterMobileDevices;
import org.ar.linclick.distributed.function.filters.FilterPcDevices;
import org.ar.linclick.distributed.function.mappers.MapCountryCodeToIntCounter;
import org.ar.linclick.distributed.function.mappers.MapOsNameToIntCounter;
import org.ar.linclick.distributed.function.mappers.MapPlatformNameToIntCounter;
import org.ar.linclick.distributed.function.reducers.SummarizeIntValues;
import org.ar.linclick.driver.reader.CassandraReader;
import scala.Tuple2;

import java.util.List;

/**
 * Apache spark driver and statistic processing pipeline.
 */
public class SparkDriver {
  private static final String SPARK_APP_NAME = "LinClick";
  private static final String CASSANDRA_HOST_PROPERTY = "spark.cassandra.connection.host";

  private final JavaSparkContext sparkContext;
  private final CassandraReader cassandraReader;

  public SparkDriver(String masterNodePath, String cassandraHost, String[] jarsPaths){
    SparkConf sparkConf = new SparkConf().setAppName(SPARK_APP_NAME)
        .setMaster(masterNodePath)
        .set(CASSANDRA_HOST_PROPERTY, cassandraHost).setJars(jarsPaths);
    this.sparkContext = new JavaSparkContext(sparkConf);
    this.cassandraReader = new CassandraReader(sparkContext);
  }

  public ClientInfoResult processClientInfoData(String shortUrlId) {
    JavaRDD<ClientInfo> processingData = cassandraReader.readClientInfoData(shortUrlId);

    List<Tuple2<String, Integer>> countPlatforms = processingData
        .mapToPair(new MapPlatformNameToIntCounter())
        .reduceByKey(new SummarizeIntValues()).collect();

    List<Tuple2<String, Integer>> countCountries = processingData
        .mapToPair(new MapCountryCodeToIntCounter())
        .reduceByKey(new SummarizeIntValues()).collect();

    List<Tuple2<String, Integer>> countMobileOs = processingData
        .filter(new FilterMobileDevices())
        .mapToPair(new MapOsNameToIntCounter())
        .reduceByKey(new SummarizeIntValues()).collect();

    List<Tuple2<String, Integer>> countPcOs = processingData
        .filter(new FilterPcDevices())
        .mapToPair(new MapOsNameToIntCounter())
        .reduceByKey(new SummarizeIntValues()).collect();
    return new ClientInfoResult(countPlatforms, countCountries, countMobileOs, countPcOs);
  }
}
