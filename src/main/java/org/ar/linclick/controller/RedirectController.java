package org.ar.linclick.controller;

import org.ar.linclick.entity.ClientDevice;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.services.LinkService;
import org.ar.linclick.util.IpUtils;
import org.ar.linclick.util.UserAgentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arymar on 09.12.15.
 */
@Controller
@RequestMapping("lc")
public class RedirectController {

  @Autowired
  private LinkService linkService;

  @RequestMapping("/{shortLink}")
  public void goToDestination(HttpServletRequest request, HttpServletResponse response, @PathVariable String shortLink) throws IOException {

    ClientInfo clientInfo = new ClientInfo();

    clientInfo.setIp(IpUtils.getIpFromRequest(request));
    clientInfo.setClientDevice(UserAgentUtil.retrieveOS(request.getHeader(HttpHeaders.USER_AGENT)));

    linkService.saveClientInfo(shortLink, clientInfo);
    response.sendRedirect(linkService.getOriginalByShort(shortLink));
  }

}
