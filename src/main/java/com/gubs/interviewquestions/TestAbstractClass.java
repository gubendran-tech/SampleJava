/**
 * 
 */
package com.gubs.interviewquestions;

import org.slf4j.Logger;

/**
 * @author gubs
 * 
 * Most of the times abstract class is used in the application framework 
 * if many class wants to inherit the class which has standard methods like startup and shutdown they inherit abstract class
 * Methods in abstract class should have final. Because we don't want somebody to override 
 * Abstract class will have abstract methods (Signature). So, inherited subclass need to implement abstract methods   
 * 
 */
public abstract class TestAbstractClass {
	// final variable is like constant. You cannot override / change. It creates upon class creation.
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(TestAbstractClass.class);
	
	/**
	 * Final method cannot be override in the subclass
	 */
	protected final void testFinalMethodInAbstractClass() {
		log.info("testFinalMethodInAbstractClass...");
	}
	
	// abstract classes can have abstract methods (Signature). 
	// So, inherited subclass need to implement those methods

	protected abstract void testAbstractMethod();
	
}
