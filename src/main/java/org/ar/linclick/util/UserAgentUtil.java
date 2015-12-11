package org.ar.linclick.util;

import org.ar.linclick.entity.ClientDevice;

/**
 * Created by arymar on 11.12.15.
 */
public final class UserAgentUtil {

  private UserAgentUtil(){
    throw new UnsupportedOperationException();
  }

  public static ClientDevice retrieveOS(String userAgent){
    if (isPlatformTheSame(userAgent, "Apple-")) {
      return new ClientDevice("Mobile", "iOS");
    } else if (isPlatformTheSame(userAgent, "Android")) {
      return new ClientDevice("Mobile", "Android");
    } else if (isPlatformTheSame(userAgent, "Windows Phone")) {
      return new ClientDevice("Mobile", "Windows");
    } else if (isPlatformTheSame(userAgent, "Profile/MIDP")) {
      return new ClientDevice("Mobile", "Java");
    } else if (isPlatformTheSame(userAgent, "Windows")) {
      return new ClientDevice("PC", "Windows");
    } else if (isPlatformTheSame(userAgent, "Mac OS")) {
      return new ClientDevice("PC", "MacOS");
    } else if (isPlatformTheSame(userAgent, "X11")) {
      return new ClientDevice("PC", "Linux");
    }

    return new ClientDevice("Unknown", "Unknown");
  }

  private static boolean isPlatformTheSame(String userAgent, String platform){
    return userAgent.toLowerCase().contains(platform.toLowerCase());
  }
}
