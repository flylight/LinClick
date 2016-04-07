package org.ar.linclick.driver.reader;

import com.datastax.spark.connector.japi.CassandraJavaUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.distributed.entity.ClientDevice;
import org.ar.linclick.distributed.entity.ClientInfo;
import org.ar.linclick.distributed.function.filters.FilterClicksByShortUrlId;
import org.ar.linclick.distributed.function.mappers.MapCassandraResultsToClientInfo;

/**
 * Cassandra Reader pipeline. Read results from cassandra by Short URL Id.
 */
public class CassandraReader {
  private static final String NAME_SPACE = "linkclick";
  private static final String TABLE = "devices";

  private JavaSparkContext javaSparkContext;

  public CassandraReader(JavaSparkContext javaSparkContext) {
    this.javaSparkContext = javaSparkContext;
  }

  public JavaRDD<ClientInfo> readClientInfoData(String shortUrlId){
    return CassandraJavaUtil.javaFunctions(javaSparkContext).cassandraTable(NAME_SPACE, TABLE)
        .filter(new FilterClicksByShortUrlId(shortUrlId)).map(new MapCassandraResultsToClientInfo());
  }

}
