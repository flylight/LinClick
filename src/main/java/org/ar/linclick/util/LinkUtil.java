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

  public static String generateRundomShortUrlIdentifier(){
    RANDOMIZER.setSeed(System.currentTimeMillis());
    return new BigInteger(64, RANDOMIZER).toString(32);
  }

  public static String generateCorrectShortURL(StringBuffer requestUrl, String requestUri, String shortUrl){
    String homePath = getHomePath(requestUrl, requestUri);

    return String.format(SHORT_URL_TEMPLATE, homePath, shortUrl);
  }

  public static String normalizeUniqueLinkFromShortLink(String shortLink, StringBuffer requestUrl, String requestUri){
    String homePath = LinkUtil.getHomePath(requestUrl, requestUri)+"/lc/";

    return shortLink.substring(shortLink.indexOf(homePath)+homePath.length());

  }

  public static String getHomePath(StringBuffer requestUrl, String requestUri) {
    return requestUrl.delete(requestUrl.indexOf(requestUri),
        requestUrl.indexOf(requestUri)+requestUri.length()).toString();
  }
}
