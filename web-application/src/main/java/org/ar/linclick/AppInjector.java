package org.ar.linclick;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.ar.linclick.dao.ClientInfoDao;
import org.ar.linclick.dao.ClientInfoDaoImpl;
import org.ar.linclick.dao.LinkDao;
import org.ar.linclick.dao.LinkDaoImpl;
import org.ar.linclick.services.LinkService;
import org.ar.linclick.services.LinkServiceImpl;
import org.ar.linclick.services.StatisticService;
import org.ar.linclick.services.StatisticServiceImpl;

/**
 * Created by arymar on 13.02.16.
 */
public class AppInjector extends AbstractModule {

  @Override
  protected void configure() {
    bind(ClientInfoDao.class).to(ClientInfoDaoImpl.class);
    bind(LinkDao.class).to(LinkDaoImpl.class);
    bind(LinkService.class).to(LinkServiceImpl.class);
    bind(StatisticService.class).to(StatisticServiceImpl.class);
  }


  @Provides
  public Session databaseSession(){
    Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
    return cluster.connect("LinkClick");
  }

  @Provides
  public JavaSparkContext sparkContext(){
    SparkConf sparkConf = new SparkConf().setAppName("LinClick").setMaster("local");
    return new JavaSparkContext(sparkConf);
  }
}
