package org.ar.linclick.dao;

import org.ar.linclick.entity.ClientInfo;

import java.util.List;

/**
 * Created by arymar on 11.12.15.
 */
public interface ClientInfoDao {

  void saveClientInfo(String shortUrlId, ClientInfo clientInfo);

  List<ClientInfo> loadClientInfoByShortUrlId(String shortUrlId);

}
