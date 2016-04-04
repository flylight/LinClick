package org.ar.linclick.web.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.ar.linclick.web.dao.ClientInfoDao;
import org.ar.linclick.web.dao.LinkDao;
import org.ar.linclick.distributed.entity.ClientInfo;

/**
 * Created by arymar on 10.12.15.
 */
@Singleton
public class LinkServiceImpl implements LinkService {

  @Inject
  private ClientInfoDao clientInfoDAO;

  @Inject
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
