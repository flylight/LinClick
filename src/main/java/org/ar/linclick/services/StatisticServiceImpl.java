package org.ar.linclick.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.dao.ClientInfoDao;
import org.ar.linclick.dto.Statistic;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.util.UserAgentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by arymar on 04.01.16.
 */
@Service
public class StatisticServiceImpl implements StatisticService {

  @Autowired
  private ClientInfoDao clientInfoDao;

  @Autowired
  private JavaSparkContext sparkContext;

  @Override
  public Statistic getStatisticByShortUlrId(String shortUrlId) {
    Statistic statistic = new Statistic();

    List<ClientInfo> allData = clientInfoDao.loadClientInfoByShortUrlId(shortUrlId);

    statistic.setTotalClicks(allData.size());

    JavaRDD<ClientInfo> processingData = sparkContext.parallelize(allData);
    processingData.cache();

    //Separate all clicks by two group
    JavaRDD<ClientInfo> phoneClicks = processingData.filter(
        clientInfo -> clientInfo.getClientDevice().getPlatform()
            .equals(UserAgentUtil.DevicePlatform.MOBILE.name()));

    JavaRDD<ClientInfo> pcClicks = processingData.filter(
        clientInfo -> clientInfo.getClientDevice().getPlatform()
            .equals(UserAgentUtil.DevicePlatform.PC.name())
    );

    statistic.setPhoneClicks((int) phoneClicks.count());
    statistic.setPcClicks((int) pcClicks.count());

    //Calculate all phone statistic
    calculatePhoneStatistic(statistic, phoneClicks);
    //Calculate PC statistic
    calculatePcStatistic(statistic, pcClicks);
    //Calculate Percentages
    calculatePercentageParts(statistic);

    //TODO Implement ... EMPTY (For now)
    statistic.setSeries(new Object[0][0]);
    return statistic;
  }

  private void calculatePhoneStatistic(Statistic statistic, JavaRDD<ClientInfo> clicksInfo){
    statistic.setPhoneAndroidClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.ANDROID.name())).count());
    statistic.setPhoneJavaClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.JAVA.name())).count());
    statistic.setPhoneWinmobClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.WINDOWS.name())).count());
    statistic.setPhoneIOSClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.IOS.name())).count());
    statistic.setPhoneUnknownClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.UNKNOWN.name())).count());
  }

  private void calculatePcStatistic(Statistic statistic, JavaRDD<ClientInfo> clicksInfo){
    statistic.setPcLinuxClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.LINUX.name())).count());
    statistic.setPcWindowsClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.WINDOWS.name())).count());
    statistic.setPcMacosClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.MACOS.name())).count());
    statistic.setPcUnknownClicks((int) clicksInfo.filter(
        clickInfo -> clickInfo.getClientDevice().getOs()
            .equals(UserAgentUtil.DeviceOS.UNKNOWN.name())).count());
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
