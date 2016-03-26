package org.ar.linclick.functions.mappers;

import org.apache.spark.api.java.function.PairFunction;
import org.ar.linclick.entity.ClientInfo;
import scala.Tuple2;

/**
 * Map Device OS name to initial int counter
 */
public class MapOsNameToIntCounter implements PairFunction<ClientInfo, String, Integer> {
  @Override
  public Tuple2<String, Integer> call(ClientInfo clientInfo) throws Exception {
    return new Tuple2<>(clientInfo.getClientDevice().getOs(), 1);
  }
}
