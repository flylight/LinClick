package org.ar.linclick.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arymar on 10.12.15.
 */

@Service
public class LinkServiceImpl implements LinkService {
  private static final Map<String, String> database = new HashMap<>();


  @Override
  public void saveLink(String originalLink, String shortLink) {
    database.put(shortLink, originalLink);
  }

  @Override
  public String getOriginalByShort(String shortLink) {
    return database.get(shortLink);
  }
}
