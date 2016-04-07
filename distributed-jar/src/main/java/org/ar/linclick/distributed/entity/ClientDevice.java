package org.ar.linclick.distributed.entity;

import java.io.Serializable;

/**
 * Client device data cintainer.
 */
public class ClientDevice implements Serializable {
  private String platform;
  private String os;

  public ClientDevice(String platform, String os){
    this.platform = platform;
    this.os = os;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }
}
