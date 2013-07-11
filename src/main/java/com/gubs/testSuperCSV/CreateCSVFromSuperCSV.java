package com.gubs.testSuperCSV;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class CreateCSVFromSuperCSV {

  /**
   * @param args
   */
  public static void main(String[] args) {
    ICsvBeanWriter beanWritter = null;
    String inputPath = "/home/gubs/superCSVTest.csv";
    Customer customer = new Customer();
    customer.setId("1");
    customer.setFirstName("Gubendran");
    customer.setLastName("Lakshmanan");

    /*
     * long start = new Date().getTime(); writeIntoCSV(beanWritter, inputPath, customer);
     * System.out.println("Write into CSV done ");
     */

    readFromCSV(inputPath);
    long end = new Date().getTime();
    // System.out.println("Read from CSV done, Time Taken" + (end - start) / 1000);

  }

  private static void readFromCSV(String inputPath) {
    try {
      ICsvBeanReader reader = new CsvBeanReader(new FileReader(inputPath), CsvPreference.STANDARD_PREFERENCE);
      final String[] readHeader = reader.getCSVHeader(true);
      Customer customerRead;

      while ((customerRead = reader
          .read(Customer.class, new Customer().getReadHeaders(), new Customer().readProcessors)) != null) {
        System.out.println("Customer " + customerRead.getFirstName());
      }

    } catch (Exception e) {
      System.out.println("Exception on Reading ");
    }
  }

  private static void writeIntoCSV(ICsvBeanWriter beanWritter, String inputPath, Customer customer) {
    try {
      beanWritter = new CsvBeanWriter(new FileWriter(inputPath), CsvPreference.STANDARD_PREFERENCE);
      beanWritter.writeHeader(new Customer().getHeaders());

      beanWritter.write(customer, new Customer().getHeaders(), new Customer().getReadProcessors());

    } catch (Exception e) {
      System.out.println("The file cannot be created " + e.getMessage());
      e.printStackTrace();
    } finally {
      if (beanWritter != null) {
        try {
          beanWritter.close();
        } catch (IOException e) {
          System.out.println("Failed to close the created file " + e.getMessage());
          e.printStackTrace();
        }
      }
    }
  }
}
