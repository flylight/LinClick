package org.ar.linclick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arymar on 09.12.15.
 */
@Controller
@RequestMapping("/")
public class MainController {

  @RequestMapping(method = RequestMethod.GET)
  public String getHomePage(){
    return "home";
  }

}
