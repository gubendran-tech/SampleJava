/**
 * 
 */
package com.gubs.json;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * @author gubs
 *
 */
public class ConvertXMLtoJSON {

  private static final Logger log = Logger.getLogger(ConvertXMLtoJSON.class);

  /**
   * @param args
   */
  public static int PRETTY_PRINT_INDENT_FACTOR = 4;
  public static String TEST_XML_STRING = "<breakfast_menu>" + "<food>" + "<name>Belgian Waffles</name>"
      + "<price>$5.95</price>" + "<description>" + "Two of our famous Belgian Waffles with plenty of real maple syrup"
      + "</description>" + "<calories>650</calories>" + "</food>" + "<food>"
      + "<name>Strawberry Belgian Waffles</name>" + "<price>$7.95</price>" + "<description>"
      + "Light Belgian waffles covered with strawberries and whipped cream" + "</description>"
      + "<calories>900</calories>" + "</food>" + "</breakfast_menu>";

  public static void main(String[] args) {
    try {
      // XML to JSON
      JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);

      String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
      log.info(jsonPrettyPrintString);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
