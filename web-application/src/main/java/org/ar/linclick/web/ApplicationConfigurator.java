package org.ar.linclick.web;

import com.google.common.net.HttpHeaders;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.ar.linclick.web.dto.HttpResponse;
import org.ar.linclick.distributed.entity.ClientInfo;
import org.ar.linclick.web.entity.Statistic;
import org.ar.linclick.web.services.LinkService;
import org.ar.linclick.web.services.StatisticService;
import org.ar.linclick.web.util.IpUtils;
import org.ar.linclick.web.util.LinkUtil;
import org.ar.linclick.distributed.util.UserAgentUtil;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Main class that initialize and configure REST endpoints and resolve dependencies..
 */
public class ApplicationConfigurator {

  private LinkService linkService;
  private StatisticService statisticService;

  public ApplicationConfigurator(){
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
      clientInfo.setClientDevice(UserAgentUtil
              .retrieveOS(request.raw().getHeader(HttpHeaders.USER_AGENT)));

      linkService.saveClientInfo(shortLink, clientInfo);
      response.raw().sendRedirect(linkService.getOriginalByShort(shortLink));
      return 302;
    });

    post("/statistic/calculate", (request, response) -> {
      String uniqueLinkId =
          LinkUtil.normalizeUniqueLinkFromShortLink(request.raw().getParameter("shortUrl"),
              request.raw().getRequestURL(), request.raw().getRequestURI());
      Statistic statistic = statisticService.getStatisticByShortUlrId(uniqueLinkId);
      return new HttpResponse<>(200, statistic).toJSON();
    });
  }
}
