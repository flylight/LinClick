package org.ar.linclick.services;

import org.ar.linclick.entity.ClientInfo;

import java.util.List;

/**
 * Created by arymar on 10.12.15.
 */
public interface LinkService {

  void saveLink(String originalLink, String shortUrlId);

  String getOriginalByShort(String shortUrlId);

  void saveClientInfo(String shortUrlId, ClientInfo clientInfo);
}
