package com.gubs.testJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * http://blogs.msdn.com/b/brian_swan/archive/2011/03/02/getting-started-with-the-sql-server-jdbc-driver.aspx
 * Download sqljdbc4.jar
 * 
 * @author gubs
 * 
 */
public class JdbcSqlServerExample {

  private static final Logger log = Logger.getLogger(JdbcSqlServerExample.class);

  /**
   * @param args
   */
  public static void main(String[] args) {

    Connection conn = null;

    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      String url = "jdbc:sqlserver://172.26.1.81:1433;DatabaseName=AssetManagement";

      conn = DriverManager.getConnection(url, "Petrasolar", "p3tras0lar");

      String sql = "SELECT SN FROM Systemlocation";

      Statement stmnt = conn.createStatement();
      ResultSet rs = stmnt.executeQuery(sql);

      while (rs.next()) {
        log.info(rs.getString("SN"));
      }

      log.info("Connected..");

    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
