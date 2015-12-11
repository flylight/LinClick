package org.ar.linclick.dao;

/**
 * Created by arymar on 11.12.15.
 */
public interface LinkDao {

  void saveOriginalUrl(String shortUrlId, String originalUrl);

  String loadOriginalUrlByShortUrlId(String shortUrlId);

}
