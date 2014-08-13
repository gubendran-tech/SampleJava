/**
 * 
 */
package com.gubs.testJdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * @author gubs
 * 
 */
public class JdbcToExtractKwhSMSReportData {

  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://172.30.1.121/sms_dev_db";

  private static final String DB_USER = "prod_admin_user";
  private static final String DB_PWD = "1nt3ll1v13w";

  private static final Logger log = Logger.getLogger(JdbcToExtractKwhSMSReportData.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    Statement insertStmt = null;
    ResultSet rs = null;
    BufferedWriter bw = null;
    BufferedReader br = null;
    String fileName = "/home/gubs/Desktop/zero_device.txt";

    /*
     * try { bw = new BufferedWriter(new FileWriter(new File(fileName))); } catch (IOException e1) {
     * log.info("Failed to create a file.."); e1.printStackTrace(); }
     */

    try {
      // Step 2: Register JDBC Driver
      Class.forName(JDBC_DRIVER);

      // Step 3 : Open a connection
      log.info("Connecting to database..");
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

      // Step 4 : Execute a query
      /*
       * log.info("Creating a statement "); stmt = conn.createStatement(); String sql =
       * "SELECT DISTINCT ZIGBEE_MAC_ID FROM SMS_REPORT_DATA WHERE CUSTOMER_ID = 1"; rs =
       * stmt.executeQuery(sql);
       * 
       * // Step 5 : Extract data from result set while (rs.next()) { // int id = rs.getInt("id"); String
       * zigbeeMacId = rs.getString("ZIGBEE_MAC_ID"); bw.write(zigbeeMacId); bw.write("\n"); }
       */

      br = new BufferedReader(new FileReader(new File(fileName)));
      bw = new BufferedWriter(new FileWriter(new File("/home/gubs/Desktop/zero_device_result_newmonth.csv"), true));
      String deviceId;
      stmt = conn.createStatement();
      long startTime = new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone("EST"))).withTime(0, 0, 0, 0)
          .withDayOfMonth(1).getMillis();

      long endTime = new DateTime(startTime, DateTimeZone.forTimeZone(TimeZone.getTimeZone("EST")))
          .withTime(0, 0, 0, 0).plusMonths(1).getMillis();

      log.info(startTime);
      log.info(endTime);

      while ((deviceId = br.readLine()) != null) {
        String sql = "SELECT DEVICE_ID, ZIGBEE_MAC_ID, MIN(KWH) AS MIN_KWH, MAX(KWH) AS MAX_KWH, (MAX(KWH) - MIN(KWH)) AS SUM_KWH FROM SMS_REPORT_DATA WHERE CUSTOMER_ID = 1 AND DEVICE_ID = "
            + deviceId + " AND SEM_TIME BETWEEN " + startTime + " AND " + endTime + " ;";
        rs = stmt.executeQuery(sql);

        while (rs.next()) {
          int resDeviceId = rs.getInt("DEVICE_ID");
          String zigbeeMacId = rs.getString("ZIGBEE_MAC_ID");
          double minKwh = rs.getDouble("MIN_KWH");
          double maxKwh = rs.getDouble("MAX_KWH");
          double subKwh = rs.getDouble("SUM_KWH");

          StringBuilder str = new StringBuilder();
          str.append(resDeviceId + ",");
          str.append(zigbeeMacId + ",");
          str.append(minKwh + ",");
          str.append(maxKwh + ",");
          str.append(subKwh);

          log.info(str.toString());
          bw.write(str.toString());
          bw.write("\n");
        }

      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      log.info("Failed to write into file..");
      e.printStackTrace();
    }

    // Step 6 : Clean-up environment
    try {
      rs.close();
      stmt.close();
      conn.close();
      br.close();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {

    }

    log.info("Completed.." + new Date().toString());
  }
}
