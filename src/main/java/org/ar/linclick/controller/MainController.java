package org.ar.linclick.controller;

import org.ar.linclick.dto.HttpResponse;
import org.ar.linclick.services.LinkService;
import org.ar.linclick.util.LinkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by arymar on 09.12.15.
 */
@Controller
@RequestMapping("/")
public class MainController {

  @Autowired
  private LinkService linkService;

  @RequestMapping(method = RequestMethod.GET)
  public String getHomePage(){
    return "home";
  }


  @RequestMapping(value = "get-short-url", method = RequestMethod.POST)
  @ResponseBody
  public HttpResponse<String> getShortUrlBasedOnOriginal(@RequestParam String originalUrl, HttpServletRequest request)
      throws UnsupportedEncodingException {
    String shortUrl = LinkUtil.generateRundomShortUrlIdentifier();
    linkService.saveLink(originalUrl, shortUrl);
    return new HttpResponse<>(HttpStatus.OK, LinkUtil.generateCorrectShortURL(request
        .getRequestURL(), request.getRequestURI(), shortUrl));
  }

}
