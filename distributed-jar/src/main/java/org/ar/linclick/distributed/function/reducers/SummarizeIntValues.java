package org.ar.linclick.distributed.function.reducers;


import org.apache.spark.api.java.function.Function2;

/**
 * Reducer that will summarize Integers values.
 */
public class SummarizeIntValues implements Function2<Integer, Integer, Integer> {
  @Override public Integer call(Integer integer, Integer integer2) throws Exception {
    return integer + integer2;
  }
}
