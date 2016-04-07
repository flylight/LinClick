package org.ar.linclick.web.dao;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.ar.linclick.distributed.entity.ClientInfo;

import java.util.Optional;

/**
 * Cassandra Client Info Data Access Layer.
 */
@Singleton
public class ClientInfoDaoImpl implements ClientInfoDao {
  private Optional<PreparedStatement> insertClientInfoOptionalStatement;

  @Inject
  private Session databaseSession;

  @Override
  public void saveClientInfo(String shortUrlId, ClientInfo clientInfo) {
    PreparedStatement statement = insertClientInfoOptionalStatement.orElseGet(() -> {
      insertClientInfoOptionalStatement = Optional.of(databaseSession
          .prepare("INSERT INTO devices (date, shortUrlId, ip, os_name, os_platform) VALUES (?, ?, ?, ?, ?)"));
      return insertClientInfoOptionalStatement.get();
    });
    databaseSession.execute(statement.bind(System.currentTimeMillis(),shortUrlId, clientInfo.getIp(),
        clientInfo.getClientDevice().getOs(),clientInfo.getClientDevice().getPlatform()));
  }
}
