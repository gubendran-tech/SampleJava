/**
 * 
 */
package com.gubs.hibernateFrameWork;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gubs
 * 
 */
public class StockDailyRecord implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int recordId;
  private Float priceOpen;
  private Float priceClose;
  private Float priceChange;
  private Long volume;
  private Date date;

  private Stock stock;

  public int getRecordId() {
    return recordId;
  }

  public void setRecordId(int recordId) {
    this.recordId = recordId;
  }

  public Float getPriceOpen() {
    return priceOpen;
  }

  public void setPriceOpen(Float priceOpen) {
    this.priceOpen = priceOpen;
  }

  public Float getPriceClose() {
    return priceClose;
  }

  public void setPriceClose(Float priceClose) {
    this.priceClose = priceClose;
  }

  public Float getPriceChange() {
    return priceChange;
  }

  public void setPriceChange(Float priceChange) {
    this.priceChange = priceChange;
  }

  public Long getVolume() {
    return volume;
  }

  public void setVolume(Long volume) {
    this.volume = volume;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

}
