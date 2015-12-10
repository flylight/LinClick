package org.ar.linclick.controller;

import org.ar.linclick.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public void goToDestination(HttpServletResponse response, @PathVariable String shortLink) throws IOException {
    response.sendRedirect(linkService.getOriginalByShort(shortLink));
  }

}
