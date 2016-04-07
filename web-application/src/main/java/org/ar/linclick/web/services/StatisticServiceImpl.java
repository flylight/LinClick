package org.ar.linclick.web.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.ar.linclick.driver.SparkDriver;
import org.ar.linclick.web.dao.ClientInfoDao;
import org.ar.linclick.driver.entity.ClientInfoResult;
import org.ar.linclick.web.entity.Statistic;
import org.ar.linclick.distributed.util.UserAgentUtil;
import org.ar.linclick.distributed.util.UserAgentUtil.DeviceOS;
import scala.Tuple2;

import java.util.List;

/**
 * Statistic service.
 */
@Singleton
public class StatisticServiceImpl implements StatisticService {
  @Inject
  private SparkDriver sparkContext;

  @Override
  public Statistic getStatisticByShortUlrId(String shortUrlId) {
    ClientInfoResult processedResult = sparkContext.processClientInfoData(shortUrlId);

    Statistic statistic = new Statistic();
    statistic.setSeries(mapCountryStatistic(processedResult.getCountryCounter()));
    mapMobileStatistic(processedResult.getMobileOsCounter(), statistic);
    mapPcStatistic(processedResult.getPcOsCounter(), statistic);
    mapPlatforms(processedResult.getPlatformCounter(), statistic);
    return statistic;
  }

  private Object[][] mapCountryStatistic(List<Tuple2<String, Integer>> countryStatistic){
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
      int value = pcOsToCount._2;
      statistic.setTotalClicks(statistic.getTotalClicks() + value);
      switch (os){
        case PC:
          statistic.setPcClicks(value);
          break;
        case MOBILE:
          statistic.setPhoneClicks(pcOsToCount._2);
          break;
      }
    });
  }
}
