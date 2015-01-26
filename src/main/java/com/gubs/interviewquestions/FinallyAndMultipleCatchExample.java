/**
 * 
 */
package com.gubs.interviewquestions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gubs
 *
 * Exception handler try block should have either catch or finally as mandatory
 * Catch block will get only if exception matches. 
 * Finally block will execute irrespective if try block fails / succeed. 
 * Finally block uses for closing the external opened resource pool (Printer, file, devices)..
 * Finally will not execute only if you have System.exit in try block / catch block
 * In java7 ARM (Automatic Resource Management concept) java handles on its own to avoid boiler plate code
 */
public class FinallyAndMultipleCatchExample {

	private static final Logger log = LoggerFactory.getLogger(FinallyAndMultipleCatchExample.class);
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, Exception {
		new FinallyAndMultipleCatchExample().methodReader();
		new FinallyAndMultipleCatchExample().methodWritter();
	}
	
	private void methodWritter() throws IOException, Exception {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("/home/gubs/dummy.txt"));
			bw.write("Dummy testing..");
			log.info("Data written to the file..");
//			System.exit(0);
		} catch (IOException ioException) {
			throw new IOException("Failed to write data into file");
			// Exception catch should be in the last. Most of the exception class inherit Exception. 
			// So, exception catch should be at-least
		} catch (Exception exp) {
			throw new Exception("Failed with Exception..");
		} finally {
			// If file handle not close properly then content may not written into file..
			if (bw != null)
				bw.close();
		}
	}
	private void methodReader() throws IOException {
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader("/home/gubs/1.txt"));
			String line = null;
			while((line = br.readLine()) != null) {
				log.info("Input {}", line);
			}
		} catch (IOException ioexception) {
			throw new IOException("Failed to read the file");
		} finally {
			if (br != null)
				br.close();
		}
	}

}
