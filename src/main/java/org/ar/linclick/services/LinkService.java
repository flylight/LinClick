package org.ar.linclick.services;

/**
 * Created by arymar on 10.12.15.
 */
public interface LinkService {
  void saveLink(String originalLink, String shortLink);
  String getOriginalByShort(String shortLink);
}
