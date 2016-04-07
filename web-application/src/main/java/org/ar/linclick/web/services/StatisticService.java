package org.ar.linclick.web.services;

import org.ar.linclick.web.entity.Statistic;

/**
 * Statistic service.
 */
public interface StatisticService {
  Statistic getStatisticByShortUlrId(String shortUrlId);
}
