package com.gubs.hibernateFrameWork;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;


public class TestHibernateOneToMany {

  private static final Logger log = Logger.getLogger(TestHibernateOneToMany.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    log.info("Hibernate one to many (XML Mapping)");
    Session session = HibernateUtil.getSessionFactory().openSession();

    session.beginTransaction();

    Stock stock = new Stock();
    stock.setStockCode("7052");
    stock.setStockName("PADINI");
    session.save(stock);

    StockDailyRecord stockDailyRecords = new StockDailyRecord();
    stockDailyRecords.setPriceOpen(new Float("1.2"));
    stockDailyRecords.setPriceClose(new Float("1.1"));
    stockDailyRecords.setPriceChange(new Float("10.0"));
    stockDailyRecords.setVolume(3000000L);
    stockDailyRecords.setDate(new Date());

    stockDailyRecords.setStock(stock);
    stock.getStockDailyRecords().add(stockDailyRecords);

    session.save(stockDailyRecords);

    session.getTransaction().commit();
    log.info("Done");
  }

}
