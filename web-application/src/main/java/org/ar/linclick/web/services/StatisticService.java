package org.ar.linclick.web.services;

import org.ar.linclick.web.entity.Statistic;

/**
 * Created by arymar on 04.01.16.
 */
public interface StatisticService {
  Statistic getStatisticByShortUlrId(String shortUrlId);
}
