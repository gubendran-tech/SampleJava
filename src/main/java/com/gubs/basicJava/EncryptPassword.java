/**
 * 
 */
package com.gubs.basicJava;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * @author gubs
 * 
 */
public class EncryptPassword {

  /**
   * @param args
   */
  private static final Logger log = Logger.getLogger(EncryptPassword.class);

  public static void main(String[] args) {
    PasswordEncoder encoder = new Md5PasswordEncoder();
    String encryptpwd = encoder.encodePassword("gubs", null);
    log.info("Encrypted password.." + encryptpwd);

  }

}
