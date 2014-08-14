package com.gubs.basicJava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

public class FileOperations {

  private static final Logger log = Logger.getLogger(FileOperations.class);

  private static final String path = File.separator + "home" + File.separator + "gubs" + File.separator;
  private static final String pathName = path + "dummy.txt";

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    File test = new File("/home" + File.separatorChar + "gubs");
    log.info(test);

    FileOperations file = new FileOperations();

    // Create or Write a File
    file.createOrWriteFile();

    // Read data from a file
    file.readFromFile();

    // Test FileOperations
    file.fileOperations();

  }

  private void fileOperations() {
    File file = new File(path + File.separator + "folder1" + File.separator + "folder2");
    file.mkdirs();

    File paths = new File(
        "/appserver/apache-tomcat-7.0.50/webapps/SMSService/WEB-INF/classes/BAPCO_GridWaveCommands.xml");

    log.info("FileName.." + paths.getName());

    log.info("Only FilePath.." + paths.getParent());
  }

  private void readFromFile() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(new File(pathName)));
    String currentLine = null;
    while ((currentLine = br.readLine()) != null) {
      log.info(currentLine);
    }
    br.close();
  }

  private void createOrWriteFile() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(pathName), true));
    bw.write(new DateTime(System.currentTimeMillis()).toString() + " Gubs Testing Write" + "\n");
    bw.flush();
    bw.close();
  }

}
