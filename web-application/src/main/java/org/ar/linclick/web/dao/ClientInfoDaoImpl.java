package org.ar.linclick.web.dao;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.ar.linclick.distributed.entity.ClientInfo;

/**
 * Created by arymar on 11.12.15.
 */
@Singleton
public class ClientInfoDaoImpl implements ClientInfoDao {

  @Inject
  private Session databaseSession;

  @Override
  public void saveClientInfo(String shortUrlId, ClientInfo clientInfo) {
    PreparedStatement statement = databaseSession
        .prepare("INSERT INTO devices (date, shortUrlId, ip, os_name, os_platform) VALUES (?, ?, ?, ?, ?)");
    databaseSession.execute(statement.bind(System.currentTimeMillis(),shortUrlId, clientInfo.getIp(),
        clientInfo.getClientDevice().getOs(),clientInfo.getClientDevice().getPlatform()));
  }
}
