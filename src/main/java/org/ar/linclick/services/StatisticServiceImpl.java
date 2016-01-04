package org.ar.linclick.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.ar.linclick.dao.ClientInfoDao;
import org.ar.linclick.dto.Statistic;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.util.UserAgentUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by arymar on 04.01.16.
 */
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

    //calculate phone part of clicks
    statistic.setPhonePart((int) processingData.filter(
        clientInfo -> clientInfo.getClientDevice().getPlatform()
                .equals(UserAgentUtil.DevicePlatform.MOBILE.name()))
        .count()
    );

    //calculate pc part of clicks


    return statistic;
  }
}
