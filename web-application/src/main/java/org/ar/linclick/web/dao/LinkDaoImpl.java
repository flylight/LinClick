package org.ar.linclick.web.dao;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by arymar on 11.12.15.
 */
@Singleton
public class LinkDaoImpl implements LinkDao {

  @Inject
  private Session databaseSession;

  @Override
  public void saveOriginalUrl(String shortUrlId, String originalUrl) {
    PreparedStatement statement = databaseSession.prepare("INSERT INTO links (shortUrlId,  originalUrl) VALUES (?, ?)");
    databaseSession.execute(statement.bind(shortUrlId, originalUrl));
  }

  @Override
  public String loadOriginalUrlByShortUrlId(String shortUrlId) {
    PreparedStatement statement = databaseSession.prepare("SELECT originalUrl FROM links WHERE shortUrlId=?");
    ResultSet resultSet = databaseSession.execute(statement.bind(shortUrlId));
    return resultSet.one().getString("originalUrl");
  }
}
