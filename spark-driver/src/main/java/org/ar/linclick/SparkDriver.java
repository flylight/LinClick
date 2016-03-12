package org.ar.linclick;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.entity.Statistic;
import org.ar.linclick.functions.PcSeparationFunction;
import org.ar.linclick.functions.PhoneSeparationFunction;
import org.ar.linclick.functions.reducers.SummarizeIntValues;
import org.ar.linclick.utils.UserAgentUtil;
import scala.Tuple2;

import java.util.List;

/**
 * Created by arymar on 13.02.16.
 */
public class SparkDriver {
  private final JavaSparkContext sparkContext;

  public SparkDriver(){
    SparkConf sparkConf = new SparkConf().setAppName("LinClick").setMaster("local");
    sparkContext = new JavaSparkContext(sparkConf);
  }

  public Statistic getStatisticByShortUlrId(List<ClientInfo> allData) {
    Statistic statistic = new Statistic();
    statistic.setTotalClicks(allData.size());

    JavaRDD<ClientInfo> processingData = sparkContext.parallelize(allData);
    processingData.cache();


    List<Tuple2<String, Integer>> countPlatforms = processingData.mapToPair(clientInfo -> new Tuple2<>(clientInfo.getClientDevice().getPlatform(), 1))
        .reduceByKey(new SummarizeIntValues()).collect();

    List<Tuple2<String, Integer>> countMobileOs = processingData
        .filter(clientInfo -> clientInfo.getClientDevice().getPlatform().equals(UserAgentUtil.DevicePlatform.MOBILE.name()))
        .mapToPair(clientInfo -> new Tuple2<>(clientInfo.getClientDevice().getOs(), 1))
        .reduceByKey(new SummarizeIntValues()).collect();

    List<Tuple2<String, Integer>> countPcOs = processingData
        .filter(clientInfo -> clientInfo.getClientDevice().getPlatform().equals(UserAgentUtil.DevicePlatform.PC.name()))
        .mapToPair(clientInfo -> new Tuple2<>(clientInfo.getClientDevice().getOs(), 1))
        .reduceByKey(new SummarizeIntValues()).collect();


    calculatePercentageParts(statistic);

    //TODO Implement ... EMPTY (For now)
    statistic.setSeries(new Object[0][0]);
    return statistic;
  }


  public void calculatePercentageParts(Statistic statistic){
    if(statistic.getTotalClicks() > 0) {
      statistic.setPhonePart((statistic.getPhoneClicks() / statistic.getTotalClicks()) * 100);
      statistic.setPcPart((statistic.getPcClicks() / statistic.getTotalClicks()) * 100);
      if(statistic.getPhoneClicks() > 0){
        statistic.setPhoneAndroidPart(
            (int)((statistic.getPhoneAndroidClicks() / (double) statistic.getPhoneClicks()) * 100));
        statistic.setPhoneWinmobPart(
            (int)((statistic.getPhoneWinmobClicks() / (double) statistic.getPhoneClicks()) * 100));
        statistic.setPhoneIOSPart(
            (int)((statistic.getPhoneIOSClicks() / (double) statistic.getPhoneClicks()) * 100));
        statistic.setPhoneJavaPart(
            (int)((statistic.getPhoneJavaClicks() / (double) statistic.getPhoneClicks()) * 100));
        statistic.setPhoneUnknownPart(
            (int)((statistic.getPhoneUnknownClicks() / (double) statistic.getPhoneClicks()) * 100));
      }
      if(statistic.getPcClicks() > 0) {
        statistic.setPcWindowsPart(
            (int)((statistic.getPcWindowsClicks() / (double) statistic.getPcClicks()) * 100));
        statistic.setPcLinuxPart(
            (int)((statistic.getPcLinuxClicks() / (double) statistic.getPcClicks()) * 100));
        statistic.setPcMacosPart(
            (int)((statistic.getPcMacosClicks() / (double) statistic.getPcClicks()) * 100));
        statistic.setPcUnknownPart(
            (int)((statistic.getPcUnknownClicks() / (double) statistic.getPcClicks()) * 100));
      }
    }
  }

}
