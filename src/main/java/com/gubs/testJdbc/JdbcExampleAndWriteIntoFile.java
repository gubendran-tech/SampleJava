package com.gubs.testJdbc;

// Step 1 : import packages required
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author gubs
 *
 */
public class JdbcExampleAndWriteIntoFile {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";
	
	static final String  DB_USER = "root";
	static final String  DB_PWD  = "mysql";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// Step 2: Register JDBC Driver
			Class.forName(JDBC_DRIVER);
			
			// Step 3 : Open a connection
			System.out.println("Connecting to database..");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			
			//Step 4 : Execute a query
			System.out.println("Creating a statement ");
			stmt = conn.createStatement();
			String sql = "SELECT id, name FROM dummy WHERE name REGEXP 'Replaced with unit : System SN - [0-9a-z]'";
			rs = stmt.executeQuery(sql);
			
			//Step 5 : Extract data from result set
			while(rs.next()) {
//				int id = rs.getInt("id");
				String name = rs.getString("name");
				
//				System.out.println("Id " + id );
				System.out.println("Content : " + name);
				pregMatch(name);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Step 6 : Clean-up environment
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * This method used to match the input string with the pregmatch and pass the content to create a file	
	 * @param name
	 */
	private static void pregMatch(String name) {
		String strTest = "Replaced with unit : System SN - 188120409P132 Panel SN - 2120107202516 " +
				"Inverter MacAddress - 6F0000C847D4 Inverter SN - 128120124J979";
		
		String pattern = "(Replaced with unit : System SN - )(.*)( Panel SN - )(.*)(Inverter MacAddress - )(.*)( Inverter SN - )(.*)";
		
		//Create pattern object
		Pattern r = Pattern.compile(pattern);
		
		// Now create matcher object
		Matcher m = r.matcher(strTest);
		String systemSerialNumber = null;
		String panelSerialNumber = null;
		String inverterMacAddress = null;
		String inverterSerialNumber = null;
		
		while(m.find()) {
//			System.out.println("Group 0 prints all " + m.group(0));
			systemSerialNumber = m.group(2);
			panelSerialNumber = m.group(4);
			inverterMacAddress = m.group(6);
			inverterSerialNumber = m.group(8);
			System.out.println("System Serial Number : " + systemSerialNumber);
			System.out.println("Panel Serial Number : " + panelSerialNumber);
			System.out.println("Inverter MacAddress : " + inverterMacAddress);
			System.out.println("Inverter SN : " + inverterSerialNumber);
			writeIntoFile(systemSerialNumber, panelSerialNumber, inverterMacAddress, inverterSerialNumber);
		}
	}

	/**
	 * This method used to write the input content into file
	 * 
	 * @param systemSerialNumber
	 * @param panelSerialNumber
	 * @param inverterMacAddress
	 * @param inverterSerialNumber
	 */
	private static void writeIntoFile(String systemSerialNumber,
			String panelSerialNumber, String inverterMacAddress,
			String inverterSerialNumber) {
		
		File file = new File("/home/gubs/output.txt");
		if (!file.exists()) {
			try {
				System.out.println("File created");
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("File failed to create ");
				e.printStackTrace();
			}
		}
		
		try {
			// Note : If the second argument true the byte content will be appended else it will write the content from begin
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			StringBuilder strBuilder = new StringBuilder();
			bw.newLine();
			strBuilder.append("System Serial Number : " + systemSerialNumber);
			strBuilder.append(" Panel Serial Number : " + panelSerialNumber);
			strBuilder.append(" Inverter MacAddress : " + inverterMacAddress);
			strBuilder.append(" Inverter SN : " + inverterSerialNumber);
			
			bw.write(strBuilder.toString());
			bw.flush();
			bw.close();
			System.out.println("Done..");
			
		} catch (IOException e) {
			System.out.println("IO Exception on FileWriter ");
			e.printStackTrace();
		}
		
	}
}
