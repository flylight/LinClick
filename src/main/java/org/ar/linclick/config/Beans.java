package org.ar.linclick.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by arymar on 11.12.15.
 */
@Configuration
public class Beans {

  @Bean
  public Session databaseSession(){
    Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
    return cluster.connect("LinkClick");
  }

}
