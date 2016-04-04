package org.ar.linclick.distributed.function.mappers;

import com.datastax.spark.connector.japi.CassandraRow;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.ar.linclick.distributed.entity.ClientDevice;
import org.ar.linclick.distributed.entity.ClientInfo;
import scala.Tuple2;

/**
 * Map cassandra row into client info object
 */
public class MapCassandraResultsToClientInfo implements Function<CassandraRow, ClientInfo> {
  @Override
  public ClientInfo call(CassandraRow row) throws Exception {
    ClientInfo clientInfo = new ClientInfo();
    clientInfo.setClientDevice(
        new ClientDevice(row.getString("os_platform"), row.getString("os_name")));
    clientInfo.setIp(row.getString("ip"));
    clientInfo.setCountryCode("UKR");
    return clientInfo;
  }
}
