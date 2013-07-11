package com.gubs.basicJava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author gubs
 * 
 *         http://www.tutorialspoint.com/java/java_regular_expressions.htm groups will print the matched group
 *         text
 */
public class TestRegExp {

  /**
   * @param args
   */
  public static void main(String[] args) {
    /*
     * String strTest = "This is gubs salary 10000k in jeanmartin"; String pattern = "(.*?)(\\d+)(.*)";
     * 
     * //Create pattern object Pattern r = Pattern.compile(pattern);
     * 
     * // Now create matcher object Matcher m = r.matcher(strTest); while(m.find()) {
     * System.out.println("Found Value " + m.group(3)); }
     */

    String input = "<rci_request version=\"1.1\"><do_command target=\"NMS_Custom_RCI_Callback\"><rci_cmd_struct><command><id v= \"LC_Set_Schedule\"/><schedule Day='Daily' Hour='19' Minute='2' Action='Light' LightValue='On' Target='G1' /><schedule Day='Daily' Hour='19' Minute='7' Action='Sensor' SensorValue='Off' Target='G1' /></command></rci_cmd_struct></do_command></rci_request>";

    String pattern = "<schedule (.*?)/>";
    Pattern pat = Pattern.compile(pattern);
    Matcher m = pat.matcher(input);

    while (m.find()) {
      System.out.println(m.group(1));
    }

    /*
     * String strTest = "Replaced with unit : System SN - 188120409P132 Panel SN - 2120107202516 " +
     * "Inverter MacAddress - 6F0000C847D4 Inverter SN - 128120124J979";
     * 
     * String pattern =
     * "(Replaced with unit : System SN - )(.*)( Panel SN - )(.*)(Inverter MacAddress - )(.*)( Inverter SN - )(.*)"
     * ;
     * 
     * // Create pattern object Pattern r = Pattern.compile(pattern);
     * 
     * // Now create matcher object Matcher m = r.matcher(strTest); while (m.find()) { //
     * System.out.println("Group 0 prints all " + m.group(0)); System.out.println("System Serial Number " +
     * m.group(2)); System.out.println("Panel Serial Number " + m.group(4));
     * System.out.println("Inverter MacAddress " + m.group(6)); System.out.println("Inverter SN " +
     * m.group(8)); }
     */

  }
}
