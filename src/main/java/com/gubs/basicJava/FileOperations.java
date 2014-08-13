package com.gubs.basicJava;

import java.io.File;

import org.apache.log4j.Logger;

public class FileOperations {

  private static final Logger log = Logger.getLogger(FileOperations.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    File file = new File("/home" + File.separatorChar + "gubs");
    log.info(file);
  }

}
