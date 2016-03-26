package org.ar.linclick.reader;

import com.datastax.spark.connector.japi.CassandraJavaUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.entity.ClientDevice;
import org.ar.linclick.entity.ClientInfo;

/**
 * Created by arymar on 26.03.16.
 */
public class CassandraReader {

  private JavaSparkContext javaSparkContext;

  public CassandraReader(JavaSparkContext javaSparkContext) {
    this.javaSparkContext = javaSparkContext;
  }

  public JavaRDD<ClientInfo> readClientInfoData(String shortUrlId){
    return CassandraJavaUtil.javaFunctions(javaSparkContext).cassandraTable("LinkClick", "devices")
        .filter(row -> row.getString("shortUrlId").equals(shortUrlId))
        .map(row -> {
          ClientInfo clientInfo = new ClientInfo();
          clientInfo.setClientDevice(
              new ClientDevice(row.getString("os_platform"), row.getString("os_name")));
          clientInfo.setIp(row.getString("ip"));
          clientInfo.setCountryCode("UKR");
          return clientInfo;
    });
  }

}
