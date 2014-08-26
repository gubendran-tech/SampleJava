package com.gubs.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

public class SampleFTPClient {

  private static final Logger log = Logger.getLogger(SampleFTPClient.class);

  /**
   * @param args
   */
  public static void main(String[] args) {

    String server = "ftp2.petrasolar.com";
    String username = "jmi";
    int port = 21;
    String password = "jmi123";

    FTPClient ftpClient = new FTPClient();
    try {
      ftpClient.connect(server, port);
      ftpClient.login(username, password);
      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

      // Retrieve files
      String remoteAddress = "/jmi/ToJMI/PSEG/RMAShipment/";
      String fileName = "RMA20140201.csv.hst";
      String localAddress = "/home/gubs/Petra-JMI/mdl/";

      OutputStream outputStream = new BufferedOutputStream(
          new FileOutputStream(new File(localAddress + fileName), true));

      boolean success = ftpClient.retrieveFile(remoteAddress + fileName, outputStream);
      outputStream.close();
      if (success) {
        log.info("File Download successfully " + localAddress + fileName);
      }

    } catch (SocketException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
