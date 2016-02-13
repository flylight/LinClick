package org.ar.linclick.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.dao.ClientInfoDao;
import org.ar.linclick.entity.Statistic;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.util.UserAgentUtil;

import java.util.List;

/**
 * Created by arymar on 04.01.16.
 */
@Singleton
public class StatisticServiceImpl implements StatisticService {

  @Inject
  private ClientInfoDao clientInfoDao;

  @Inject
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

  //    Object[][] series = {
  //        {"BLR",75},{"BLZ",43},{"RUS",50},{"RWA",88},{"SRB",21},{"TLS",43},
  //        {"REU",21},{"TKM",19},{"TJK",60},{"ROU",4},{"TKL",44},{"GNB",38},
  //        {"GUM",67},{"GTM",2},{"SGS",95},{"GRC",60},{"GNQ",57},{"GLP",53},
  //        {"JPN",59},{"GUY",24},{"GGY",4},{"GUF",21},{"GEO",42},{"GRD",65},
  //        {"GBR",14},{"GAB",47},{"SLV",15},{"GIN",19},{"GMB",63},{"GRL",56},
  //        {"ERI",57},{"MNE",93},{"MDA",39},{"MDG",71},{"MAF",16},{"MAR",8},
  //        {"MCO",25},{"UZB",81},{"MMR",21},{"MLI",95},{"MAC",33},{"MNG",93},
  //        {"MHL",15},{"MKD",52},{"MUS",19},{"MLT",69},{"MWI",37},{"MDV",44},
  //        {"MTQ",13},{"MNP",21},{"MSR",89},{"MRT",20},{"IMN",72},{"UGA",59},
  //        {"TZA",62},{"MYS",75},{"MEX",80},{"ISR",77},{"FRA",54},{"IOT",56},
  //        {"SHN",91},{"FIN",51},{"FJI",22},{"FLK",4},{"FSM",69},{"FRO",70},
  //        {"NIC",66},{"NLD",53},{"NOR",7},{"NAM",63},{"VUT",15},{"NCL",66},
  //        {"NER",34},{"NFK",33},{"NGA",45},{"NZL",96},{"NPL",21},{"NRU",13},
  //        {"NIU",6},{"COK",19},{"XKX",32},{"CIV",27},{"CHE",65},{"COL",64},
  //        {"CHN",16},{"CMR",70},{"CHL",15},{"CCK",85},{"CAN",76},{"COG",20},
  //        {"CAF",93},{"COD",36},{"CZE",77},{"CYP",65},{"CXR",14},{"CRI",31},
  //        {"CUW",67},{"CPV",63},{"CUB",40},{"SWZ",58},{"SYR",96},{"SXM",31},
  //        {"UKR", 99}};
}
