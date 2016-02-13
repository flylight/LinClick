package org.ar.linclick;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.httpclient.HttpStatus;
import org.ar.linclick.AppInjector;
import org.ar.linclick.dto.HttpResponse;
import org.ar.linclick.entity.ClientInfo;
import org.ar.linclick.entity.Statistic;
import org.ar.linclick.services.LinkService;
import org.ar.linclick.services.StatisticService;
import org.ar.linclick.util.IpUtils;
import org.ar.linclick.util.LinkUtil;
import org.ar.linclick.util.UserAgentUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.ws.rs.core.HttpHeaders;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by arymar on 13.02.16.
 */
public class WebApplication {

  private LinkService linkService;
  private StatisticService statisticService;

  public WebApplication(){
    configureDependencyInjections();
    configureRestEndpoints();
  }

  public void configureDependencyInjections(){
    Injector injector = Guice.createInjector(new AppInjector());

    linkService = injector.getInstance(LinkService.class);
    statisticService = injector.getInstance(StatisticService.class);
  }

  public void configureRestEndpoints(){
    staticFileLocation("/public");

    post("/get-short-url", (request, response) -> {
      String shortUrl = LinkUtil.generateRundomShortUrlIdentifier();
      linkService.saveLink(request.raw().getParameter("originalUrl"), shortUrl);
      return new HttpResponse<>(200, LinkUtil.generateCorrectShortURL(request.raw().getRequestURL(),
          request.raw().getRequestURI(), shortUrl)).toJSON();
    });

    get("/lc/:shortLink", (request, response) -> {
      String shortLink = request.params(":shortLink");

      ClientInfo clientInfo = new ClientInfo();

      clientInfo.setIp(IpUtils.getIpFromRequest(request.raw()));
      clientInfo.setClientDevice(
          UserAgentUtil.retrieveOS(request.raw().getHeader(HttpHeaders.USER_AGENT)));

      linkService.saveClientInfo(shortLink, clientInfo);
      response.raw().sendRedirect(linkService.getOriginalByShort(shortLink));
      return HttpStatus.SC_MOVED_TEMPORARILY;
    });

    post("/statistic/calculate", (request, response) -> {
      String uniqueLinkId =
          LinkUtil.normalizeUniqueLinkFromShortLink(request.raw().getParameter("shortUrl"),
              request.raw().getRequestURL(), request.raw().getRequestURI());
      Statistic statistic = statisticService.getStatisticByShortUlrId(uniqueLinkId);
      return new HttpResponse<>(HttpStatus.SC_OK, statistic).toJSON();
    });
  }
}
