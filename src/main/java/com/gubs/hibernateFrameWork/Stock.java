package com.gubs.hibernateFrameWork;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// http://www.mkyong.com/hibernate/hibernate-one-to-many-relationship-example/
public class Stock implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Integer stockId;
  private String stockCode;
  private String stockName;

  private Set<StockDailyRecord> stockDailyRecords = new HashSet<StockDailyRecord>();

  public Integer getStockId() {
    return stockId;
  }

  public void setStockId(Integer stockId) {
    this.stockId = stockId;
  }

  public String getStockCode() {
    return stockCode;
  }

  public void setStockCode(String stockCode) {
    this.stockCode = stockCode;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public Set<StockDailyRecord> getStockDailyRecords() {
    return stockDailyRecords;
  }

  public void setStockDailyRecords(Set<StockDailyRecord> stockDailyRecords) {
    this.stockDailyRecords = stockDailyRecords;
  }

}
