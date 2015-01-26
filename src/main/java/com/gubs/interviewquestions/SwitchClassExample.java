/**
 * 
 */
package com.gubs.interviewquestions;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gubs
 *
 * 
 * 
 */
public class SwitchClassExample {

	private static Logger logger = LoggerFactory.getLogger(SwitchClassExample.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Enter the input id for the weekDay id with int switch");
		new SwitchClassExample().switchIntExample();
		logger.info("Enter the Input id for weekDay Id");
		Scanner scan = new Scanner(System.in);
		new SwitchClassExample().switchEnumExample(EnumWeekDays.getByWeekId(scan.nextInt()));
	}

	private void switchEnumExample(EnumWeekDays enumWeekDays) {
		switch(enumWeekDays) {
		case SUNDAY:
			logger.info("Sunday enum has input..");
			break;
		case MONDAY:
			logger.info("Monday enum has input..");
			break;
		case TUESDAY:
			logger.info("Tuesday enum has input..");
			break;
		default:
			logger.info("The input is not exactly correct..");
			break;
		}
	}

	private void switchIntExample() {
//		while (true) {
			Scanner sc = new Scanner(System.in);
			// JDK 6 support switch for Enum and int only
			switch (sc.nextInt()) {
			case 0:
				logger.info("Case 0 Sunday");
				break;
			case 1:
				logger.info("Case 1 Monday");
				break;
			case 2:
				logger.info("Case 2 Tuesday");
				break;
			default:
				logger.info("It came to default. Type 0..2");
				break;
			}
		}
//	}

}
