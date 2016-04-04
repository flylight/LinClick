package org.ar.linclick.distributed.entity;

import java.io.Serializable;

/**
 * Created by arymar on 11.12.15.
 */
public class ClientInfo implements Serializable {
  private String ip;
  private ClientDevice clientDevice;
  private String countryCode;

  public ClientInfo() {
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public ClientDevice getClientDevice() {
    return clientDevice;
  }

  public void setClientDevice(ClientDevice clientDevice) {
    this.clientDevice = clientDevice;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
}
