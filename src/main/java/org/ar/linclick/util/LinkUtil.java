package org.ar.linclick.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by arymar on 10.12.15.
 */
public final class LinkUtil {
  private static final SecureRandom RANDOMIZER = new SecureRandom();
  private static final String SHORT_URL_TEMPLATE = "%1s/lc/%2s";

  private LinkUtil(){
    throw new UnsupportedOperationException();
  }

  public static String gererateRundomShortUrlIdentifier(){
    RANDOMIZER.setSeed(System.currentTimeMillis());
    return new BigInteger(64, RANDOMIZER).toString(32);
  }

  public static String generateCorrectShortURL(StringBuffer requestUrl, String requestUri, String shortUrl){
    StringBuffer homePath = requestUrl.delete(requestUrl.indexOf(requestUri),
        requestUrl.indexOf(requestUri)+requestUri.length());

    return String.format(SHORT_URL_TEMPLATE, homePath.toString(), shortUrl);
  }
}
