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

  private static final String CASSANDRA_CLUSTER_NODE = "172.22.61.115";
  private static final String CASSANDRA_NAMESPACE = "LinkClick";
  private static final String SPARK_MASTER_NODE = "spark://172.22.61.115:7077";
  private static final String SPARK_DISTRIBUTED_JAR_PATH = "/workspace/projects/LinClick/distributed-jar/target/distributed-jar-1.0.0-fat.jar";

  @Override
  protected void configure() {
    bind(ClientInfoDao.class).to(ClientInfoDaoImpl.class);
    bind(LinkDao.class).to(LinkDaoImpl.class);
    bind(LinkService.class).to(LinkServiceImpl.class);
    bind(StatisticService.class).to(StatisticServiceImpl.class);
  }


  @Provides
  public Session databaseSession(){
    Cluster cluster = Cluster.builder().addContactPoint(CASSANDRA_CLUSTER_NODE).build();
    return cluster.connect(CASSANDRA_NAMESPACE);
  }

  @Provides
  public SparkDriver sparkDriver(){
    return new SparkDriver(SPARK_MASTER_NODE, CASSANDRA_CLUSTER_NODE,
        new String[]{SPARK_DISTRIBUTED_JAR_PATH});
  }
}
