/**
 * 
 */
package com.gubs.testJdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author gubs
 * 
 */
public class JdbcToExtractSMSReportData {

  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://172.26.3.46/sms_dev_db";

  private static final String DB_USER = "qa_admin_user";
  private static final String DB_PWD = "petra123";

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
    String fileName = "/home/gubs/dev_sms_report_data.csv";

    /*
     * try { bw = new BufferedWriter(new FileWriter(new File(fileName))); } catch (IOException e1) {
     * System.out.println("Failed to create a file.."); e1.printStackTrace(); }
     */

    try {
      // Step 2: Register JDBC Driver
      Class.forName(JDBC_DRIVER);

      // Step 3 : Open a connection
      System.out.println("Connecting to database..");
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

      // Step 4 : Execute a query
      /*
       * System.out.println("Creating a statement "); stmt = conn.createStatement(); String sql =
       * "SELECT DISTINCT ZIGBEE_MAC_ID FROM SMS_REPORT_DATA WHERE CUSTOMER_ID = 1"; rs =
       * stmt.executeQuery(sql);
       * 
       * // Step 5 : Extract data from result set while (rs.next()) { // int id = rs.getInt("id"); String
       * zigbeeMacId = rs.getString("ZIGBEE_MAC_ID"); bw.write(zigbeeMacId); bw.write("\n"); }
       */

      br = new BufferedReader(new FileReader(new File(fileName)));
      String readZigbee;
      stmt = conn.createStatement();
      insertStmt = conn.createStatement();
      while ((readZigbee = br.readLine()) != null) {
        String sql = "SELECT DEVICE_ID, ZIGBEE_MAC_ID, KWH FROM SMS_REPORT_DATA WHERE CUSTOMER_ID = 1 AND ZIGBEE_MAC_ID = '"
            + readZigbee + "' ORDER BY ID DESC LIMIT 1;";
        rs = stmt.executeQuery(sql);

        // Step 5 : Extract data from result set
        while (rs.next()) {
          String zigbeeMacId = rs.getString("ZIGBEE_MAC_ID");
          System.out.println("Zigbee.." + zigbeeMacId);
          Double kwh = rs.getDouble("KWH");
          int deviceId = rs.getInt("DEVICE_ID");
          StringBuilder insertSql = new StringBuilder();
          insertSql
              .append("INSERT INTO ZIGBEE_KWH_LOOKUP_HISTORY_SMS_REPORT(CUSTOMER_ID, DEVICE_ID, ZIGBEE_MAC_ID, KWH) VALUES(1,");
          insertSql.append(deviceId + ",'");
          insertSql.append(zigbeeMacId + "',");
          insertSql.append(kwh + ");");

          insertStmt.executeUpdate(insertSql.toString());
        }
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Failed to write into file..");
      e.printStackTrace();
    }

    // Step 6 : Clean-up environment
    try {
      rs.close();
      stmt.close();
      insertStmt.close();
      conn.close();
      br.close();
      // bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {

    }

    System.out.println("Completed.." + new Date().toString());
  }
}
