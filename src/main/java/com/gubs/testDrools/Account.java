package com.gubs.testDrools;

public class Account {

  private int Id;
  private String name;
  private double voltage;
  private int configuredV;

  public Account(int id, String name, double voltage, int configuredV) {
    super();
    Id = id;
    this.name = name;
    this.voltage = voltage;
    this.configuredV = configuredV;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getVoltage() {
    return voltage;
  }

  public void setVoltage(double voltage) {
    this.voltage = voltage;
  }

  public int getConfiguredV() {
    return configuredV;
  }

  public void setConfiguredV(int configuredV) {
    this.configuredV = configuredV;
  }

}
