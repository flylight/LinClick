package org.ar.linclick.dao;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import org.ar.linclick.entity.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by arymar on 11.12.15.
 */
@Repository
public class ClientInfoDaoImpl implements ClientInfoDao {

  @Autowired
  private Session databaseSession;

  @Override
  public void saveClientInfo(String shortUrlId, ClientInfo clientInfo) {
    PreparedStatement statement = databaseSession
        .prepare("INSERT INTO devices (date, shortUrlId, ip, os_name, os_platform) VALUES (?, ?, ?, ?, ?)");
    databaseSession.execute(statement.bind(System.currentTimeMillis(),shortUrlId, clientInfo.getIp(),
        clientInfo.getClientDevice().getOs(),clientInfo.getClientDevice().getPlatform()));
  }

  @Override
  public List<ClientInfo> loadClientInfoByShortUrlId(String shortUrlId) {
    return null;
  }
}
