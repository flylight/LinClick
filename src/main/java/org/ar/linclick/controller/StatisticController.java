package org.ar.linclick.controller;

import org.ar.linclick.dto.HttpResponse;
import org.ar.linclick.dto.Statistic;
import org.ar.linclick.services.StatisticService;
import org.ar.linclick.util.LinkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by arymar on 11.12.15.
 */
@Controller
@RequestMapping("/statistic")
public class StatisticController {

  @Autowired
  private StatisticService statisticService;


  @RequestMapping("calculate")
  @ResponseBody
  public HttpResponse<Statistic> getStatisticByShortUrl( @RequestParam String shortUrl, HttpServletRequest request){
    String uniqueLinkId = LinkUtil.normalizeUniqueLinkFromShortLink(shortUrl,
        request.getRequestURL(), request.getRequestURI());
    Statistic statistic = statisticService.getStatisticByShortUlrId(uniqueLinkId);
    return new HttpResponse<>(HttpStatus.OK, statistic);
//    Statistic stubbyStatistic = new Statistic();
//    stubbyStatistic.setTotalClicks(500);
//    stubbyStatistic.setPcClicks(300);
//    stubbyStatistic.setPcPart(60);
//
//    stubbyStatistic.setPcLinuxClicks(100);
//    stubbyStatistic.setPcLinuxPart(33);
//    stubbyStatistic.setPcMacosClicks(150);
//    stubbyStatistic.setPcMacosPart(40);
//    stubbyStatistic.setPcWindowsClicks(49);
//    stubbyStatistic.setPcWindowsPart(26);
//    stubbyStatistic.setPcUnknownClicks(1);
//    stubbyStatistic.setPcUnknownPart(1);
//
//
//    stubbyStatistic.setPhoneClicks(200);
//    stubbyStatistic.setPhonePart(40);
//    stubbyStatistic.setPhoneAndroidClicks(50);
//    stubbyStatistic.setPhoneAndroidPart(25);
//    stubbyStatistic.setPhoneIOSClicks(50);
//    stubbyStatistic.setPhoneIOSPart(25);
//    stubbyStatistic.setPhoneJavaClicks(50);
//    stubbyStatistic.setPhoneJavaPart(25);
//    stubbyStatistic.setPhoneWinmobClicks(48);
//    stubbyStatistic.setPhoneWinmobPart(25);
//    stubbyStatistic.setPhoneUnknownClicks(2);
//    stubbyStatistic.setPhoneUnknownPart(1);
//
//    Object[][] series = {
//        {"BLR",75},{"BLZ",43},{"RUS",50},{"RWA",88},{"SRB",21},{"TLS",43},
//        {"REU",21},{"TKM",19},{"TJK",60},{"ROU",4},{"TKL",44},{"GNB",38},
//        {"GUM",67},{"GTM",2},{"SGS",95},{"GRC",60},{"GNQ",57},{"GLP",53},
//        {"JPN",59},{"GUY",24},{"GGY",4},{"GUF",21},{"GEO",42},{"GRD",65},
//        {"GBR",14},{"GAB",47},{"SLV",15},{"GIN",19},{"GMB",63},{"GRL",56},
//        {"ERI",57},{"MNE",93},{"MDA",39},{"MDG",71},{"MAF",16},{"MAR",8},
//        {"MCO",25},{"UZB",81},{"MMR",21},{"MLI",95},{"MAC",33},{"MNG",93},
//        {"MHL",15},{"MKD",52},{"MUS",19},{"MLT",69},{"MWI",37},{"MDV",44},
//        {"MTQ",13},{"MNP",21},{"MSR",89},{"MRT",20},{"IMN",72},{"UGA",59},
//        {"TZA",62},{"MYS",75},{"MEX",80},{"ISR",77},{"FRA",54},{"IOT",56},
//        {"SHN",91},{"FIN",51},{"FJI",22},{"FLK",4},{"FSM",69},{"FRO",70},
//        {"NIC",66},{"NLD",53},{"NOR",7},{"NAM",63},{"VUT",15},{"NCL",66},
//        {"NER",34},{"NFK",33},{"NGA",45},{"NZL",96},{"NPL",21},{"NRU",13},
//        {"NIU",6},{"COK",19},{"XKX",32},{"CIV",27},{"CHE",65},{"COL",64},
//        {"CHN",16},{"CMR",70},{"CHL",15},{"CCK",85},{"CAN",76},{"COG",20},
//        {"CAF",93},{"COD",36},{"CZE",77},{"CYP",65},{"CXR",14},{"CRI",31},
//        {"CUW",67},{"CPV",63},{"CUB",40},{"SWZ",58},{"SYR",96},{"SXM",31},
//        {"UKR", 99}};
//
//    stubbyStatistic.setSeries(series);
//
//    return new HttpResponse<>(HttpStatus.OK, stubbyStatistic);
  }

}
