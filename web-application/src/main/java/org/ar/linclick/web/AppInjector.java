package org.ar.linclick.web;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.ar.linclick.web.dao.ClientInfoDao;
import org.ar.linclick.web.dao.ClientInfoDaoImpl;
import org.ar.linclick.web.dao.LinkDao;
import org.ar.linclick.web.dao.LinkDaoImpl;
import org.ar.linclick.driver.SparkDriver;
import org.ar.linclick.web.services.LinkService;
import org.ar.linclick.web.services.LinkServiceImpl;
import org.ar.linclick.web.services.StatisticService;
import org.ar.linclick.web.services.StatisticServiceImpl;

/**
 * Google Guice injection instances declarations.
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
  public SparkDriver sparkDriver(){
    return new SparkDriver("spark://127.0.0.1:7077", "127.0.0.1",
        new String[]{
            "/workspace/projects/LinClick/distributed-jar/target/distributed-jar-1.0.0-fat.jar"});
  }
}
