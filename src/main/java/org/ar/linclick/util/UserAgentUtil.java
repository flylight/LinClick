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
      return new ClientDevice(DevicePlatform.MOBILE.name(), DeviceOS.IOS.name());
    } else if (isPlatformTheSame(userAgent, "Android")) {
      return new ClientDevice(DevicePlatform.MOBILE.name(), DeviceOS.ANDROID.name());
    } else if (isPlatformTheSame(userAgent, "Windows Phone")) {
      return new ClientDevice(DevicePlatform.MOBILE.name(), DeviceOS.WINDOWS.name());
    } else if (isPlatformTheSame(userAgent, "Profile/MIDP")) {
      return new ClientDevice(DevicePlatform.MOBILE.name(), DeviceOS.JAVA.name());
    } else if (isPlatformTheSame(userAgent, "Windows")) {
      return new ClientDevice(DevicePlatform.PC.name(), DeviceOS.WINDOWS.name());
    } else if (isPlatformTheSame(userAgent, "Mac OS")) {
      return new ClientDevice(DevicePlatform.PC.name(), DeviceOS.MACOS.name());
    } else if (isPlatformTheSame(userAgent, "X11")) {
      return new ClientDevice(DevicePlatform.PC.name(), DeviceOS.LINUX.name());
    }

    return new ClientDevice(DevicePlatform.UNKNOWN.name(), DeviceOS.UNKNOWN.name());
  }

  private static boolean isPlatformTheSame(String userAgent, String platform){
    return userAgent.toLowerCase().contains(platform.toLowerCase());
  }

  public enum DevicePlatform{
    MOBILE, PC, UNKNOWN
  }

  public enum DeviceOS{
    IOS, ANDROID, WINDOWS, JAVA, MACOS, LINUX, UNKNOWN
  }
}
