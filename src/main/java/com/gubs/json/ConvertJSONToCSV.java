/**
 * 
 */
package com.gubs.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author gubs
 * 
 */
public class ConvertJSONToCSV {

  private static final Logger log = Logger.getLogger(ConvertJSONToCSV.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      jsonToCSV();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void jsonToCSV() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/home/gubs/prod_jobs_schedule.csv"), false));
    bw.write("customerId,JobName,recurrence,time");
    bw.write("\n");

    FileReader reader = new FileReader(new File("/home/gubs/jobs.schedule"));
    JsonElement jelement = new JsonParser().parse(reader);
    JsonObject jobject = jelement.getAsJsonObject();
    // jobject = jobject.getAsJsonObject("schedule");
    JsonArray jarray = jobject.getAsJsonArray("schedule");

    Iterator<JsonElement> iterator = jarray.iterator();

    while (iterator.hasNext()) {
      JsonElement jelementIter = iterator.next();
      JsonObject jsonObj = jelementIter.getAsJsonObject();

      StringBuilder str = new StringBuilder();
      String id = jsonObj.get("id").toString();
      id = id.replaceAll("\"", "");

      String[] splitId = id.split("_");

      str.append(splitId[0] + ",");

      if (splitId.length > 1) {
        str.append(splitId[1] + ",");
      }

      str.append(jsonObj.get("recurrence").toString() + ",");

      String time = jsonObj.get("time").toString();
      // 2013-08-02.10.45.00.000 (Regexp below for this format)
      time = time.replaceAll("\\d+-\\d+-\\d+.(\\d+.\\d+).*", "$1");

      str.append(time + ",");

      str.append("\n");
      log.info(str.toString());
      bw.write(str.toString());
    }
    bw.close();

  }
}
