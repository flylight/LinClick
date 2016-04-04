package org.ar.linclick.web.dao;

/**
 * Created by arymar on 11.12.15.
 */
public interface LinkDao {

  void saveOriginalUrl(String shortUrlId, String originalUrl);

  String loadOriginalUrlByShortUrlId(String shortUrlId);

}
