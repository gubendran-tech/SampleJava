package com.gubs.basicJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.apache.hadoop.thirdparty.guava.common.base.Splitter;
import org.apache.hadoop.thirdparty.guava.common.collect.Sets;

import com.google.common.collect.Lists;

import edu.emory.mathcs.backport.java.util.Arrays;

public class JavaBasics {

  private enum WEEKDAYS {
    SUNDAY, MONDAY, TUESDAY
  };

  // Integer.toBinaryString - http://www.tutorialspoint.com/java/lang/integer_tobinarystring.htm

  public static void main(String[] args) throws IOException, ParseException {

    double volt = 99.97;
    double confV = 99.98;
    
    List<String> str = Lists.newArrayList("gubs", "sai", "kavi");
    for(int i=0; i <= 2; i++) {
    	System.out.println(str.get(i));
    }

    if (confV > volt) {
      System.out.println("Coming..");
    }
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MILLISECOND, 300000);
    System.out.println(cal.getTime());

    System.out.println(System.getProperty("user.home"));
    System.out.println("Value..." + Integer.toHexString(500));

    String filePath = "/home/gubs";
    String fileName = "SC_Device.csv";

    File file = new File(filePath, fileName);
    FileInputStream fs = new FileInputStream(file);
    System.out.println(fs.available());
    fs.close();

    Map<String, String> testMap = new HashMap<String, String>();
    testMap.put("058110418p033", "1");

    if (testMap.get("058110418P033") != null) {
      System.out.println("Yes it has");
    }
    /*
     * arrayDiff(); setsDiff(); arrayCopy(); // indexOf, subString, CharAt, toBinaryString javaFunctions();
     */

    List<String> ss = new ArrayList<String>();
    for (String s : ss) {
      System.out.println("Yes");
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
      System.out.println(str);

    }

    // Trim
    String validationForTrim = "23343";
    if (validationForTrim.length() != validationForTrim.trim().length()) {
      System.out.println("Space existing.." + validationForTrim.trim());
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
      System.out.println("Zigbee Index : " + zigbeeMacId);
    }

    System.out.println("Parse int " + Integer.parseInt("152.00".substring(0, "152.00".indexOf("."))));
    String testSplit = "Hier3";
    List<String> hierarchyNames = null;

    hierarchyNames = Lists.newArrayList(Splitter.on("$$").split(testSplit));
    System.out.println(hierarchyNames.get(0));

    String sub = "10111";
    char c = sub.charAt(sub.length() - 1);

    System.out.println("Substring.." + Character.getNumericValue(c));

    int i = 40;
    // Only unsigned integer from 0 - 255 converts to binaryString
    System.out.println("Integer to binary String " + Integer.toBinaryString(i));

    String byt = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');

    int j = 36;
    // Only unsigned integer from 0 - 255 converts to binaryString
    System.out.println("Integer to binary String " + Integer.toBinaryString(j));

    String byt1 = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');

    System.out.println(byt1.substring(13, byt1.length()));

    int status = Integer.parseInt(byt.substring(13, byt.length()), 2);

    System.out.println("Integer to binary String " + byt);

    System.out.println("Integer to binary Status " + status);

    String character = "10101";
    System.out.println("Character at index " + character.charAt(0));
  }

  private static void arrayCopy() {
    String[] copyFrom = new String[] { "gubs", "Sai Theja", "Kavi", "siva" };
    // Array size needs to define
    String[] copyTo = new String[2];
    // Destination position to copy
    System.arraycopy(copyFrom, 1, copyTo, 0, 2);

    System.out.println("A2 array " + Arrays.toString(copyTo));
  }

  private static void setsDiff() {
    Set<String> firstSet = Sets.newHashSet("apple", "orange");
    Set<String> secondSet = Sets.newHashSet("apple", "orange", "straw");

    Set<String> diff = Sets.difference(secondSet, firstSet);

    System.out.println("Sets difference " + diff.toString());
  }

  private static void arrayDiff() {
    String[] arr = { "apple", "orange" };
    List<String> firstList = Arrays.asList(arr);

    String[] ar1 = { "apple", "orange", "straw" };
    List<String> secondList = Arrays.asList(ar1);

    List<String> diff = ListUtils.subtract(secondList, firstList);
    System.out.println("List Difference.." + diff.toString());
  }
}
