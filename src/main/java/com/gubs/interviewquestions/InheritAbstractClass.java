/**
 * 
 */
package com.gubs.interviewquestions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gubs
 *
 */
public class InheritAbstractClass extends TestAbstractClass {

	private static final Logger log = LoggerFactory.getLogger(InheritAbstractClass.class); 
	private final String finalTest = "Final variable is like constant. Cannot be override and it assign value upon class create";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Calling abstract method..");
		new InheritAbstractClass().testAbstractClass();
		new InheritAbstractClass().testAbstractMethod();
		
		new FinalTestClass().finalTestClass();
	}

	@Override
	protected void testAbstractMethod() {
		// slf4j supports {} to replace the string (Object[]). It improves performance if info is not enabled
		log.info("Due to abstract class inheritance abstract methods need to implemented {}", finalTest);
	}
	
	private void testAbstractClass() {
		testFinalMethodInAbstractClass();
	}

}
