package org.ar.linclick.dao;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by arymar on 11.12.15.
 */
@Repository
public class LinkDaoImpl implements LinkDao {

  @Autowired
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
