package org.ar.linclick;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.entity.ClientInfoResult;
import org.ar.linclick.functions.filters.FilterMobileDevices;
import org.ar.linclick.functions.filters.FilterPcDevices;
import org.ar.linclick.functions.mappers.MapCountryCodeToIntCounter;
import org.ar.linclick.functions.mappers.MapOsNameToIntCounter;
import org.ar.linclick.functions.mappers.MapPlatformNameToIntCounter;
import org.ar.linclick.functions.reducers.SummarizeIntValues;
import org.ar.linclick.reader.CassandraReader;
import scala.Tuple2;

import java.util.List;

/**
 * Apache spark driver and statistic processing pipeline.
 */
public class SparkDriver {

  private final JavaSparkContext sparkContext;
  private final CassandraReader cassandraReader;

  public SparkDriver(String masterNodePath, String... jarsPaths){
    SparkConf sparkConf = new SparkConf().setAppName("LinClick")
        .setMaster(masterNodePath)
        .set("spark.cassandra.connection.host", "127.0.0.1")
        .setJars(jarsPaths);
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
