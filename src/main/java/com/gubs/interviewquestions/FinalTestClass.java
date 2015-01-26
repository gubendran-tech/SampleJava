/**
 * 
 */
package com.gubs.interviewquestions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gubs
 * finalTestClass cannot be subclass. Example "String" class is a final. 
 * For security reason we don't to subclass
 */
public final class FinalTestClass {
	
	private static final Logger log = LoggerFactory.getLogger(FinalTestClass.class);
	
	public void finalTestClass() {
		log.info("finalTestClass cannot be subclass. Example String class is a final. For security reason we don't to subclass");
	}
}
