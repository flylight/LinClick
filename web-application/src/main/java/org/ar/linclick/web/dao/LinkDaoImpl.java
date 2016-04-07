package org.ar.linclick.web.dao;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;

/**
 * Cassandra Data access layer.
 */
@Singleton
public class LinkDaoImpl implements LinkDao {
  private Optional<PreparedStatement> saveOriginalUrlOptionalStatement;
  private Optional<PreparedStatement> getOriginalByShortOptionalStatement;

  @Inject
  private Session databaseSession;

  @Override
  public void saveOriginalUrl(String shortUrlId, String originalUrl) {
    PreparedStatement statement = saveOriginalUrlOptionalStatement.orElseGet(() -> {
      saveOriginalUrlOptionalStatement =
          Optional.of(databaseSession.prepare("INSERT INTO links (shortUrlId,  originalUrl) VALUES (?, ?)"));
      return saveOriginalUrlOptionalStatement.get();
    });
    databaseSession.execute(statement.bind(shortUrlId, originalUrl));
  }

  @Override
  public String loadOriginalUrlByShortUrlId(String shortUrlId) {
    PreparedStatement statement = getOriginalByShortOptionalStatement.orElseGet(() -> {
      getOriginalByShortOptionalStatement =
          Optional.of(databaseSession.prepare("SELECT originalUrl FROM links WHERE shortUrlId=?"));
      return getOriginalByShortOptionalStatement.get();
    });
    ResultSet resultSet = databaseSession.execute(statement.bind(shortUrlId));
    return resultSet.one().getString("originalUrl");
  }
}
