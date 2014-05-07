package com.gubs.JAXB;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author gubs
 * 
 *         This class is to construct XML for Photovoltaic and send to MASSACHUSSETS or CC ending state
 * 
 *         http://blog.bdoughan.com/2012/11/creating-generic-list-wrapper-in-jaxb.html,
 *         http://blog.bdoughan.com/2011/08/xml-schema-to-java-generating.html
 */
@XmlRootElement(name = "ARData")
public class Photovoltaic implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String dummyElement;
  private PtsSystem ptsSystem;
  private String sysId; // HierarchyName
  private MonthlyData monthlyData;

  // Below 3 attributes transient (temp) to avoid MonthlyData and PVMonthlyData classes generation
  private Double kwhRegistry; // LifeTimeKwh
  private String dateTimeGenerated; // dateTime we generate file
  private Double serviceCost = 0D; // For now default 0

  public Photovoltaic() {

  }

  public Photovoltaic(String sysId, Double kwhRegistry, String dateTimeGenerated) {
    super();
    this.dummyElement = "ptsSystem";
    this.ptsSystem = new PtsSystem();
    this.sysId = sysId;
    PVMonthlyData pvMonthlyData = new PVMonthlyData(kwhRegistry, dateTimeGenerated);
    this.monthlyData = new MonthlyData(pvMonthlyData);
  }

  public Photovoltaic(String sysId, Double kwhRegistry, String dateTimeGenerated, Double serviceCost) {
    super();
    this.sysId = sysId;
    PVMonthlyData pvMonthlyData = new PVMonthlyData(kwhRegistry, dateTimeGenerated, serviceCost);
    this.monthlyData = new MonthlyData(pvMonthlyData);
  }

  @XmlElement(required = true, name = "sysID")
  public String getSysId() {
    return sysId;
  }

  public void setSysId(String sysId) {
    this.sysId = sysId;
  }

  @XmlElement(required = true, name = "MonthlyData")
  public MonthlyData getMonthlyData() {
    return monthlyData;
  }

  public void setMonthlyData(MonthlyData monthlyData) {
    this.monthlyData = monthlyData;
  }

  @XmlTransient
  public Double getKwhRegistry() {
    return kwhRegistry;
  }

  public void setKwhRegistry(Double kwhRegistry) {
    this.kwhRegistry = kwhRegistry;
  }

  @XmlTransient
  public String getDateTimeGenerated() {
    return dateTimeGenerated;
  }

  public void setDateTimeGenerated(String dateTimeGenerated) {
    this.dateTimeGenerated = dateTimeGenerated;
  }

  @XmlTransient
  public Double getServiceCost() {
    return serviceCost;
  }

  public void setServiceCost(Double serviceCost) {
    this.serviceCost = serviceCost;
  }

  @XmlElement(name = "ptsSystem")
  public PtsSystem getPtsSystem() {
    return ptsSystem;
  }

  public void setPtsSystem(PtsSystem ptsSystem) {
    this.ptsSystem = ptsSystem;
  }

  @XmlElement
  public String getDummyElement() {
    return dummyElement;
  }

  public void setDummyElement(String dummyElement) {
    this.dummyElement = dummyElement;
  }

}

class PtsSystem implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @XmlAttribute
  private String sysType = "PV";

  public PtsSystem() {

  }
}

class MonthlyData implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private PVMonthlyData pvMonthlyData;

  public MonthlyData() {

  }

  public MonthlyData(PVMonthlyData pvMonthlyData) {
    this.pvMonthlyData = pvMonthlyData;
  }

  @XmlElement(required = true, name = "PVMonthlyData")
  public PVMonthlyData getPvMonthlyData() {
    return pvMonthlyData;
  }

  public void setPvMonthlyData(PVMonthlyData pvMonthlyData) {
    this.pvMonthlyData = pvMonthlyData;
  }
}

class PVMonthlyData implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Double kwhRegistry; // LifeTimeKwh
  private String dateTimeGenerated; // dateTime we generate file
  private Double serviceCost = 0D; // For now default 0

  public PVMonthlyData() {

  }

  public PVMonthlyData(Double kwhRegistry, String dateTimeGenerated, Double serviceCost) {
    super();
    this.kwhRegistry = kwhRegistry;
    this.dateTimeGenerated = dateTimeGenerated;
    this.serviceCost = serviceCost;
  }

  public PVMonthlyData(Double kwhRegistry, String dateTimeGenerated) {
    super();
    this.kwhRegistry = kwhRegistry;
    this.dateTimeGenerated = dateTimeGenerated;
  }

  @XmlElement(required = true, name = "kwhRegistry")
  public Double getKwhRegistry() {
    return kwhRegistry;
  }

  public void setKwhRegistry(Double kwhRegistry) {
    this.kwhRegistry = kwhRegistry;
  }

  @XmlElement(required = true, name = "regDateTime")
  public String getDateTimeGenerated() {
    return dateTimeGenerated;
  }

  public void setDateTimeGenerated(String dateTimeGenerated) {
    this.dateTimeGenerated = dateTimeGenerated;
  }

  @XmlElement(required = false, name = "serviceCostsInDollars")
  public Double getServiceCost() {
    return serviceCost;
  }

  public void setServiceCost(Double serviceCost) {
    this.serviceCost = serviceCost;
  }
}