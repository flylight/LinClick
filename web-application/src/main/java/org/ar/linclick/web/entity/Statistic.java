package org.ar.linclick.web.entity;

import java.io.Serializable;

/**
 * Statistic container
 */
public class Statistic implements Serializable {

  private int totalClicks = 0;
  private int phoneClicks = 0;
  private int phoneAndroidClicks = 0;
  private int phoneIOSClicks = 0;
  private int phoneWinmobClicks = 0;
  private int phoneJavaClicks = 0;
  private int phoneUnknownClicks = 0;
  private int pcClicks = 0;
  private int pcLinuxClicks = 0;
  private int pcMacosClicks = 0;
  private int pcWindowsClicks = 0;
  private int pcUnknownClicks = 0;

  private Object[][] series;

  public int getTotalClicks() {
    return totalClicks;
  }

  public void setTotalClicks(int totalClicks) {
    this.totalClicks = totalClicks;
  }

  public int getPhonePart() {
    return totalClicks > 0 ? (phoneClicks / totalClicks) * 100 : 0;
  }

  public int getPhoneClicks() {
    return phoneClicks;
  }

  public void setPhoneClicks(int phoneClicks) {
    this.phoneClicks = phoneClicks;
  }

  public int getPhoneAndroidPart() {
    return phoneClicks > 0 ? (phoneAndroidClicks / phoneClicks) * 100 : 0;
  }

  public int getPhoneAndroidClicks() {
    return phoneAndroidClicks;
  }

  public void setPhoneAndroidClicks(int phoneAndroidClicks) {
    this.phoneAndroidClicks = phoneAndroidClicks;
  }

  public int getPhoneIOSPart() {
    return phoneClicks > 0 ? (phoneIOSClicks / phoneClicks) * 100 : 0;
  }

  public int getPhoneIOSClicks() {
    return phoneIOSClicks;
  }

  public int getPhoneWinmobPart() {
    return phoneClicks > 0 ? (phoneWinmobClicks / phoneClicks) * 100 : 0;
  }

  public int getPhoneWinmobClicks() {
    return phoneWinmobClicks;
  }

  public void setPhoneWinmobClicks(int phoneWinmobClicks) {
    this.phoneWinmobClicks = phoneWinmobClicks;
  }

  public int getPhoneJavaPart() {
    return phoneClicks > 0 ? (phoneJavaClicks / phoneClicks) * 100 : 0;
  }

  public int getPhoneJavaClicks() {
    return phoneJavaClicks;
  }

  public void setPhoneJavaClicks(int phoneJavaClicks) {
    this.phoneJavaClicks = phoneJavaClicks;
  }

  public int getPhoneUnknownPart() {
    return phoneClicks > 0 ? (phoneUnknownClicks / phoneClicks) * 100 : 0;
  }

  public int getPhoneUnknownClicks() {
    return phoneUnknownClicks;
  }

  public void setPhoneUnknownClicks(int phoneUnknownClicks) {
    this.phoneUnknownClicks = phoneUnknownClicks;
  }

  public int getPcPart() {
    return totalClicks > 0 ? (pcClicks / totalClicks) * 100 : 0;
  }

  public int getPcClicks() {
    return pcClicks;
  }

  public void setPcClicks(int pcClicks) {
    this.pcClicks = pcClicks;
  }

  public int getPcLinuxPart() {
    return pcClicks > 0 ? (pcLinuxClicks / pcClicks) * 100 : 0;
  }

  public int getPcLinuxClicks() {
    return pcLinuxClicks;
  }

  public void setPcLinuxClicks(int pcLinuxClicks) {
    this.pcLinuxClicks = pcLinuxClicks;
  }

  public int getPcMacosPart() {
    return pcClicks > 0 ? (pcMacosClicks / pcClicks) * 100 : 0;
  }

  public int getPcMacosClicks() {
    return pcMacosClicks;
  }

  public void setPcMacosClicks(int pcMacosClicks) {
    this.pcMacosClicks = pcMacosClicks;
  }

  public int getPcWindowsPart() {
    return pcClicks > 0 ? (pcWindowsClicks / pcClicks) * 100 : 0;
  }

  public int getPcWindowsClicks() {
    return pcWindowsClicks;
  }

  public void setPcWindowsClicks(int pcWindowsClicks) {
    this.pcWindowsClicks = pcWindowsClicks;
  }

  public int getPcUnknownPart() {
    return pcClicks > 0 ? (pcUnknownClicks / pcClicks) * 100 : 0;
  }

  public int getPcUnknownClicks() {
    return pcUnknownClicks;
  }

  public void setPcUnknownClicks(int pcUnknownClicks) {
    this.pcUnknownClicks = pcUnknownClicks;
  }

  public Object[][] getSeries() {
    return series;
  }

  public void setSeries(Object[][] series) {
    this.series = series;
  }

  public void setPhoneIOSClicks(int phoneIOSClicks) {
    this.phoneIOSClicks = phoneIOSClicks;
  }
}
