package org.ar.linclick.services;

import org.ar.linclick.dto.Statistic;

/**
 * Created by arymar on 04.01.16.
 */
public interface StatisticService {
  Statistic getStatisticByShortUlrId(String shortUrlId);
}
