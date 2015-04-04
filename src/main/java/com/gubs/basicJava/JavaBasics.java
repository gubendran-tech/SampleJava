package com.gubs.basicJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.collections.ListUtils;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class JavaBasics {

  private static final Logger log = Logger.getLogger(JavaBasics.class);

  private enum WEEKDAYS {
    SUNDAY, MONDAY, TUESDAY
  };

  // Integer.toBinaryString - http://www.tutorialspoint.com/java/lang/integer_tobinarystring.htm

  public static void main(String[] args) throws IOException, ParseException {

	  System.out.println("gubs".charAt(0));
	  Text text = new Text();
	  text.set("gubs");
	  
	StringTokenizer tokens = new StringTokenizer("Gubendran	Lakshmanan", "\t");
	while(tokens.hasMoreTokens()) {
		System.out.println(tokens.nextToken());
	}
    String[] arr = new String[] { "a", "b" };
    log.info("Array.." + Arrays.asList(arr).toString());

    log.error("Testing Error Logger level..");

    Double d = -1D;
    if (d >= 0D) {
      log.info("IF..");
    } else if (d < 0) {
      log.info("eLSE");
    }
    String remoteFilePath = "0056/IntelliView/Notifications/Reports/R3/EGS_snapshotChart_22072014_155001.png";
    String[] directories = StringUtils.tokenizeToStringArray(remoteFilePath, "/");
    String directory = "/";
    for (String directorySegment : directories) {
      directory += directorySegment + "/";
      try {
        log.info("Directory.." + directory);
      } catch (Exception e) {
        // log.info("Directory.." + directory);
      }
    }

    log.info("Default System TZ.." + TimeZone.getDefault());

    String test = "gubs";
    test = test.replace("$customerId", "0001");
    log.info(test);

    String[] levels = "H1".split("H");
    log.info("H" + levels[1]);
    log.info("Code Owner.." + EnumTest.CODE_OWNER.getName());
    log.info("Owner.." + EnumTest.get("she does everything").getName());
    double volt = 99.97;
    double confV = 99.98;
    
    List<String> str = Lists.newArrayList("gubs", "sai", "kavi");
    for(int i=0; i <= 2; i++) {
    	log.info(str.get(i));
    }

    if (confV > volt) {
      log.info("Coming..");
    }
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MILLISECOND, 300000);
    log.info(cal.getTime());

    log.info(System.getProperty("user.home"));
    log.info("Value..." + Integer.toHexString(500));

    String filePath = "/home/gubs";
    String fileName = "SC_Device.csv";

    File file = new File(filePath, fileName);
    FileInputStream fs = new FileInputStream(file);
    log.info(fs.available());
    fs.close();

    Map<String, String> testMap = new HashMap<String, String>();
    testMap.put("058110418p033", "1");

    if (testMap.get("058110418P033") != null) {
      log.info("Yes it has");
    }
    /*
     * arrayDiff(); setsDiff(); arrayCopy(); // indexOf, subString, CharAt, toBinaryString javaFunctions();
     */

    List<String> ss = new ArrayList<String>();
    for (String s : ss) {
      log.info("Yes");
    }

    javaKeywords();

  }

  private static void javaKeywords() {
    // continue function
    List<String> lists = Arrays.asList(new String[] { "apple", "orange", "strawberry" });
    Iterator<String> itr = lists.iterator();
    while (itr.hasNext()) {
      String str = itr.next();
      if (str.equalsIgnoreCase("orange")) {
        continue;
      }
      log.info(str);

    }

    // Trim
    String validationForTrim = "23343";
    if (validationForTrim.length() != validationForTrim.trim().length()) {
      log.info("Space existing.." + validationForTrim.trim());
    }
  }

  /**
   * This method have functions on java like indexOf, subString, CharAt, toBinaryString
   * 
   * @throws NumberFormatException
   */
  private static void javaFunctions() throws NumberFormatException {

    String zigbeeMacId = "6F0034343";
    if (zigbeeMacId.indexOf("6F00", 0) == 0) {
      zigbeeMacId = "000D" + zigbeeMacId;
      log.info("Zigbee Index : " + zigbeeMacId);
    }

    log.info("Parse int " + Integer.parseInt("152.00".substring(0, "152.00".indexOf("."))));
    String testSplit = "Hier3";
    List<String> hierarchyNames = null;

    hierarchyNames = Lists.newArrayList(Splitter.on("$$").split(testSplit));
    log.info(hierarchyNames.get(0));

    String sub = "10111";
    char c = sub.charAt(sub.length() - 1);

    log.info("Substring.." + Character.getNumericValue(c));

    int i = 40;
    // Only unsigned integer from 0 - 255 converts to binaryString
    log.info("Integer to binary String " + Integer.toBinaryString(i));

    String byt = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');

    int j = 36;
    // Only unsigned integer from 0 - 255 converts to binaryString
    log.info("Integer to binary String " + Integer.toBinaryString(j));

    String byt1 = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');

    log.info(byt1.substring(13, byt1.length()));

    int status = Integer.parseInt(byt.substring(13, byt.length()), 2);

    log.info("Integer to binary String " + byt);

    log.info("Integer to binary Status " + status);

    String character = "10101";
    log.info("Character at index " + character.charAt(0));
  }

  private static void arrayCopy() {
    String[] copyFrom = new String[] { "gubs", "Sai Theja", "Kavi", "siva" };
    // Array size needs to define
    String[] copyTo = new String[2];
    // Destination position to copy
    System.arraycopy(copyFrom, 1, copyTo, 0, 2);

    log.info("A2 array " + Arrays.toString(copyTo));
  }

  private static void setsDiff() {
    Set<String> firstSet = Sets.newHashSet("apple", "orange");
    Set<String> secondSet = Sets.newHashSet("apple", "orange", "straw");

    Set<String> diff = Sets.difference(secondSet, firstSet);

    log.info("Sets difference " + diff.toString());
  }

  private static void arrayDiff() {
    String[] arr = { "apple", "orange" };
    List<String> firstList = Arrays.asList(arr);

    String[] ar1 = { "apple", "orange", "straw" };
    List<String> secondList = Arrays.asList(ar1);

    List<String> diff = ListUtils.subtract(secondList, firstList);
    log.info("List Difference.." + diff.toString());
  }
}
