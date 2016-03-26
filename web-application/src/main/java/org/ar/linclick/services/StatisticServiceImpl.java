package org.ar.linclick.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.ar.linclick.SparkDriver;
import org.ar.linclick.dao.ClientInfoDao;
import org.ar.linclick.entity.ClientInfoResult;
import org.ar.linclick.entity.Statistic;
import org.ar.linclick.utils.UserAgentUtil;
import org.ar.linclick.utils.UserAgentUtil.DeviceOS;
import scala.Tuple2;

import java.util.List;

/**
 * Created by arymar on 04.01.16.
 */
@Singleton
public class StatisticServiceImpl implements StatisticService {
  @Inject
  private ClientInfoDao clientInfoDao;

  @Inject
  private SparkDriver sparkContext;

  @Override
  public Statistic getStatisticByShortUlrId(String shortUrlId) {
    ClientInfoResult processedResult = sparkContext.processClientInfoData(shortUrlId);

    Statistic statistic = new Statistic();
    //statistic.setTotalClicks(processedResult.);
    statistic.setSeries(mapCountryStatistic(processedResult.getCountryCounter()));
    mapMobileStatistic(processedResult.getMobileOsCounter(), statistic);
    mapPcStatistic(processedResult.getPcOsCounter(), statistic);
    mapPlatforms(processedResult.getPlatformCounter(), statistic);
    return statistic;
  }

  public Object[][] mapCountryStatistic(List<Tuple2<String, Integer>> countryStatistic){
    Object[][] countries = new Object[countryStatistic.size()][2];
    for (int i = 0; i < countryStatistic.size(); i++) {
      Tuple2<String, Integer> countryData = countryStatistic.get(i);
      countries[i][0] = countryData._1;
      countries[i][1] = countryData._2;
    }
    return countries;
  }

  private void mapMobileStatistic(List<Tuple2<String, Integer>> mobileStatistic, Statistic statistic){
    mobileStatistic.forEach(mobOsToCount -> {
      DeviceOS os = DeviceOS.valueOf(mobOsToCount._1);
      switch (os){
        case ANDROID :
          statistic.setPhoneAndroidClicks(mobOsToCount._2);
          break;
        case IOS:
          statistic.setPhoneIOSClicks(mobOsToCount._2);
          break;
        case WINDOWS:
          statistic.setPhoneWinmobClicks(mobOsToCount._2);
          break;
        case JAVA:
          statistic.setPhoneJavaClicks(mobOsToCount._2);
          break;
        case UNKNOWN:
          statistic.setPhoneUnknownClicks(mobOsToCount._2);
          break;
      }
    });
  }

  private void mapPcStatistic(List<Tuple2<String, Integer>> pcStatistic, Statistic statistic){
    pcStatistic.forEach(pcOsToCount -> {
      DeviceOS os = DeviceOS.valueOf(pcOsToCount._1);
      switch (os){
        case WINDOWS:
          statistic.setPcWindowsClicks(pcOsToCount._2);
          break;
        case MACOS:
          statistic.setPcMacosClicks(pcOsToCount._2);
          break;
        case LINUX:
          statistic.setPcLinuxClicks(pcOsToCount._2);
          break;
        case UNKNOWN:
          statistic.setPcUnknownClicks(pcOsToCount._2);
          break;
      }
    });
  }

  private void mapPlatforms(List<Tuple2<String, Integer>> platformStatistic, Statistic statistic){
    platformStatistic.forEach(pcOsToCount -> {
      UserAgentUtil.DevicePlatform os = UserAgentUtil.DevicePlatform.valueOf(pcOsToCount._1);
      switch (os){
        case PC:
          statistic.setPcClicks(pcOsToCount._2);
          break;
        case MOBILE:
          statistic.setPhoneClicks(pcOsToCount._2);
          break;
      }
    });
  }
}
