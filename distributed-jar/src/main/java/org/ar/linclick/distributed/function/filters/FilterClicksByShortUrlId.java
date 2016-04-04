package org.ar.linclick.distributed.function.filters;

import com.datastax.spark.connector.japi.CassandraRow;
import org.apache.spark.api.java.function.Function;

/**
 * Function to filter out Cassandra results and leave only that matched with Short URL Id.
 */
public class FilterClicksByShortUrlId implements Function<CassandraRow, Boolean> {
  private final String shortUrlId;

  public FilterClicksByShortUrlId(String shortUrlId){
    this.shortUrlId = shortUrlId;
  }

  @Override
  public Boolean call(CassandraRow cassandraRow) throws Exception {
    return cassandraRow.getString("shorturlid").equals(shortUrlId);
  }
}
