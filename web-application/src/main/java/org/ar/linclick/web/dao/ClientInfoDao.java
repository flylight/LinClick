package org.ar.linclick.web.dao;

import org.ar.linclick.distributed.entity.ClientInfo;

/**
 * Client Info Data Access Layer.
 */
public interface ClientInfoDao {
  void saveClientInfo(String shortUrlId, ClientInfo clientInfo);
}
