package org.ar.linclick.services;

import org.ar.linclick.dao.ClientInfoDao;
import org.ar.linclick.dao.LinkDao;
import org.ar.linclick.entity.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by arymar on 10.12.15.
 */

@Service
public class LinkServiceImpl implements LinkService {

  @Autowired
  private ClientInfoDao clientInfoDAO;

  @Autowired
  private LinkDao linkDAO;

  @Override
  public void saveLink(String originalLink, String shortUrlId) {
    linkDAO.saveOriginalUrl(shortUrlId, originalLink);
  }

  @Override
  public String getOriginalByShort(String shortUrlId) {
    return linkDAO.loadOriginalUrlByShortUrlId(shortUrlId);
  }

  @Override
  public void saveClientInfo(String shortUrlId, ClientInfo clientInfo) {
    clientInfoDAO.saveClientInfo(shortUrlId, clientInfo);
  }

}
