package org.ar.linclick.distributed.function.mappers;

import org.apache.spark.api.java.function.PairFunction;
import org.ar.linclick.distributed.entity.ClientInfo;
import scala.Tuple2;

/**
 * Map country code to initial counter value
 */
public class MapCountryCodeToIntCounter implements PairFunction<ClientInfo, String, Integer> {
  @Override
  public Tuple2<String, Integer> call(ClientInfo clientInfo) throws Exception {
    return new Tuple2<>(clientInfo.getCountryCode(), 1);
  }
}
