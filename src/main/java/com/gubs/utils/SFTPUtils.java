package com.gubs.utils;

/**
 * 
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author gubs
 * 
 */
public class SFTPUtils {

  private static final Logger log = Logger.getLogger(SFTPUtils.class);

  public static void main(String[] args) {
    String localFullPathWithFileName = "/home/gubs/pseg_energy_by_device/2012-01-01.csv";
    
    String ftpDetails = "sftp:$ftp-2014@172.26.103.47:22:/home/sftp/reports";

    // String ftpDetails =
    // "smsreports:smsreports@172.30.1.121:/smsreports/IntelliView/DDVReports/pseg_energy_by_device";
    String remoteFileName = "2012-01-01.csv";

    String[] ftpDetailsArray = ftpDetails.split(":");
    String[] ftpHosts = ftpDetailsArray[1].split("@");


    Map<String, String> sftpDetails = new HashMap<String, String>();

    sftpDetails.put("userName", ftpDetailsArray[0]);

    sftpDetails.put("password", ftpHosts[0]);
    sftpDetails.put("hostName", ftpHosts[1]);

    if (ftpDetailsArray.length > 3) {
      sftpDetails.put("port", ftpDetailsArray[2]);
      sftpDetails.put("remoteFilePath", ftpDetailsArray[3]);
    } else {
      sftpDetails.put("remoteFilePath", ftpDetailsArray[2]);
    }

    sftpDetails.put("remoteFileName", remoteFileName);

    
    SFTPUtils sftpUtils = new SFTPUtils();
    boolean status = false;
    try {
      status = sftpUtils.sendFile(localFullPathWithFileName, sftpDetails);
    } catch (Exception e) {
      e.printStackTrace();
    }
    log.info("Success " + status);
  }
  /**
   * 
   * @param remoteFilePath
   * @param customerId
   *          will check remote directories exists or not if not will create directories
   */
  public boolean sendFile(String localFullPathWithFileName, Map<String, String> sftpDetails) throws Exception {

    boolean status = false;

    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;

    String userName = sftpDetails.get("userName");
    String password = sftpDetails.get("password");
    String hostName = sftpDetails.get("hostName");
    Integer portName =  ((sftpDetails.get("port") != null) ? Integer.parseInt(sftpDetails.get("port")) : 22);
    String remoteFilePath = sftpDetails.get("remoteFilePath");
    String remoteFileName = sftpDetails.get("remoteFileName");

    JSch jsch;
    try {

      jsch = new JSch();
      session = jsch.getSession(userName, hostName, portName);

      session.setPassword(password);
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      session.connect();
      channel = session.openChannel("sftp");
      channel.connect();
      channelSftp = (ChannelSftp) channel;

      String[] directories = StringUtils.tokenizeToStringArray(remoteFilePath, File.separator);
      String directory = File.separator;
      for (String directorySegment : directories) {
        directory += directorySegment + File.separator;
        try {
          channelSftp.ls(directory);
        } catch (SftpException e) {
          if (e.id == 2) { // no such file
            channelSftp.mkdir(directory);
          }

        }
      }
      channelSftp.put(localFullPathWithFileName, remoteFilePath + File.separator + remoteFileName);
      status = true;

    } catch (JSchException jsEx) {
      status = false;
      jsEx.printStackTrace();
      throw jsEx;
    } catch (SftpException sftpEx) {
      status = false;
      sftpEx.printStackTrace();
      throw sftpEx;
    } finally {
      if (session != null && session.isConnected()) {
        session.disconnect();
      }
    }
    return status;
  }
}
