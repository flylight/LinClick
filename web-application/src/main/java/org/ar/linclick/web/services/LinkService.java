package org.ar.linclick.web.services;

import org.ar.linclick.distributed.entity.ClientInfo;

/**
 * Created by arymar on 10.12.15.
 */
public interface LinkService {

  void saveLink(String originalLink, String shortUrlId);

  String getOriginalByShort(String shortUrlId);

  void saveClientInfo(String shortUrlId, ClientInfo clientInfo);
}
