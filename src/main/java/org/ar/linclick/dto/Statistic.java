package org.ar.linclick.dto;

import java.io.Serializable;

/**
 * Created by arymar on 11.12.15.
 */
public class Statistic implements Serializable{

  private int totalClicks;
  private int phonePart;
  private int phoneClicks;
  private int phoneAndroidPart;
  private int phoneAndroidClicks;
  private int phoneIOSPart;
  private int phoneIOSClicks;
  private int phoneWinmobPart;
  private int phoneWinmobClicks;
  private int phoneJavaPart;
  private int phoneJavaClicks;
  private int phoneUnknownPart;
  private int phoneUnknownClicks;
  private int pcPart;
  private int pcClicks;
  private int pcLinuxPart;
  private int pcLinuxClicks;
  private int pcMacosPart;
  private int pcMacosClicks;
  private int pcWindowsPart;
  private int pcWindowsClicks;
  private int pcUnknownPart;
  private int pcUnknownClicks;

  private Object[][] series;

  public int getTotalClicks() {
    return totalClicks;
  }

  public void setTotalClicks(int totalClicks) {
    this.totalClicks = totalClicks;
  }

  public int getPhonePart() {
    return phonePart;
  }

  public void setPhonePart(int phonePart) {
    this.phonePart = phonePart;
  }

  public int getPhoneClicks() {
    return phoneClicks;
  }

  public void setPhoneClicks(int phoneClicks) {
    this.phoneClicks = phoneClicks;
  }

  public int getPhoneAndroidPart() {
    return phoneAndroidPart;
  }

  public void setPhoneAndroidPart(int phoneAndroidPart) {
    this.phoneAndroidPart = phoneAndroidPart;
  }

  public int getPhoneAndroidClicks() {
    return phoneAndroidClicks;
  }

  public void setPhoneAndroidClicks(int phoneAndroidClicks) {
    this.phoneAndroidClicks = phoneAndroidClicks;
  }

  public int getPhoneIOSPart() {
    return phoneIOSPart;
  }

  public void setPhoneIOSPart(int phoneIOSPart) {
    this.phoneIOSPart = phoneIOSPart;
  }

  public int getPhoneIOSClicks() {
    return phoneIOSClicks;
  }

  public void setPhoneIOSClicks(int phoneIOSClicks) {
    this.phoneIOSClicks = phoneIOSClicks;
  }

  public int getPhoneWinmobPart() {
    return phoneWinmobPart;
  }

  public void setPhoneWinmobPart(int phoneWinmobPart) {
    this.phoneWinmobPart = phoneWinmobPart;
  }

  public int getPhoneWinmobClicks() {
    return phoneWinmobClicks;
  }

  public void setPhoneWinmobClicks(int phoneWinmobClicks) {
    this.phoneWinmobClicks = phoneWinmobClicks;
  }

  public int getPhoneJavaPart() {
    return phoneJavaPart;
  }

  public void setPhoneJavaPart(int phoneJavaPart) {
    this.phoneJavaPart = phoneJavaPart;
  }

  public int getPhoneJavaClicks() {
    return phoneJavaClicks;
  }

  public void setPhoneJavaClicks(int phoneJavaClicks) {
    this.phoneJavaClicks = phoneJavaClicks;
  }

  public int getPhoneUnknownPart() {
    return phoneUnknownPart;
  }

  public void setPhoneUnknownPart(int phoneUnknownPart) {
    this.phoneUnknownPart = phoneUnknownPart;
  }

  public int getPhoneUnknownClicks() {
    return phoneUnknownClicks;
  }

  public void setPhoneUnknownClicks(int phoneUnknownClicks) {
    this.phoneUnknownClicks = phoneUnknownClicks;
  }

  public int getPcPart() {
    return pcPart;
  }

  public void setPcPart(int pcPart) {
    this.pcPart = pcPart;
  }

  public int getPcClicks() {
    return pcClicks;
  }

  public void setPcClicks(int pcClicks) {
    this.pcClicks = pcClicks;
  }

  public int getPcLinuxPart() {
    return pcLinuxPart;
  }

  public void setPcLinuxPart(int pcLinuxPart) {
    this.pcLinuxPart = pcLinuxPart;
  }

  public int getPcLinuxClicks() {
    return pcLinuxClicks;
  }

  public void setPcLinuxClicks(int pcLinuxClicks) {
    this.pcLinuxClicks = pcLinuxClicks;
  }

  public int getPcMacosPart() {
    return pcMacosPart;
  }

  public void setPcMacosPart(int pcMacosPart) {
    this.pcMacosPart = pcMacosPart;
  }

  public int getPcMacosClicks() {
    return pcMacosClicks;
  }

  public void setPcMacosClicks(int pcMacosClicks) {
    this.pcMacosClicks = pcMacosClicks;
  }

  public int getPcWindowsPart() {
    return pcWindowsPart;
  }

  public void setPcWindowsPart(int pcWindowsPart) {
    this.pcWindowsPart = pcWindowsPart;
  }

  public int getPcWindowsClicks() {
    return pcWindowsClicks;
  }

  public void setPcWindowsClicks(int pcWindowsClicks) {
    this.pcWindowsClicks = pcWindowsClicks;
  }

  public int getPcUnknownPart() {
    return pcUnknownPart;
  }

  public void setPcUnknownPart(int pcUnknownPart) {
    this.pcUnknownPart = pcUnknownPart;
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
}
