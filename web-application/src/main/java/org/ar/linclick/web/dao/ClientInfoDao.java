package org.ar.linclick.web.dao;

import org.ar.linclick.distributed.entity.ClientInfo;

/**
 * Created by arymar on 11.12.15.
 */
public interface ClientInfoDao {
  void saveClientInfo(String shortUrlId, ClientInfo clientInfo);
}
