package org.ar.linclick.dao;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.ar.linclick.entity.ClientDevice;
import org.ar.linclick.entity.ClientInfo;

import java.util.ArrayList;
import java.util.List;

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

  @Override
  public List<ClientInfo> loadClientInfoByShortUrlId(String shortUrlId) {
    List<ClientInfo> result = new ArrayList<>();
    PreparedStatement statement = databaseSession
        .prepare("SELECT date, shortUrlId, ip, os_name, os_platform FROM devices WHERE shortUrlId=?");
    ResultSet resultSet = databaseSession.execute(statement.bind(shortUrlId));
    for(Row row : resultSet.all()){
      ClientInfo clientInfo = new ClientInfo();
      clientInfo.setClientDevice(
          new ClientDevice(row.getString("os_platform"), row.getString("os_name")));
      clientInfo.setIp(row.getString("ip"));
      result.add(clientInfo);
    }
    return result;
  }
}
