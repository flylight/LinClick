package org.ar.linclick.web.dao;

/**
 * Link Data access layer.
 */
public interface LinkDao {

  void saveOriginalUrl(String shortUrlId, String originalUrl);

  String loadOriginalUrlByShortUrlId(String shortUrlId);

}
