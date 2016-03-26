package org.ar.linclick.entity;

import scala.Tuple2;

import java.util.List;

/**
 * Client Info pricessing result.
 */
public class ClientInfoResult {
  private final List<Tuple2<String, Integer>> platformCounter;
  private final List<Tuple2<String, Integer>> countryCounter;
  private final List<Tuple2<String, Integer>> mobileOsCounter;
  private final List<Tuple2<String, Integer>> pcOsCounter;

  public ClientInfoResult(List<Tuple2<String, Integer>> platformCounter,
      List<Tuple2<String, Integer>> countryCounter,
      List<Tuple2<String, Integer>> mobileOsCounter,
      List<Tuple2<String, Integer>> pcOsCounter) {
    this.platformCounter = platformCounter;
    this.countryCounter = countryCounter;
    this.mobileOsCounter = mobileOsCounter;
    this.pcOsCounter = pcOsCounter;
  }

  public List<Tuple2<String, Integer>> getPlatformCounter() {
    return platformCounter;
  }

  public List<Tuple2<String, Integer>> getMobileOsCounter() {
    return mobileOsCounter;
  }

  public List<Tuple2<String, Integer>> getPcOsCounter() {
    return pcOsCounter;
  }

  public List<Tuple2<String, Integer>> getCountryCounter() {
    return countryCounter;
  }
}
