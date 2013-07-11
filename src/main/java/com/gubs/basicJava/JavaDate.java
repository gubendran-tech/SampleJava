package com.gubs.basicJava;

import java.util.Calendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JavaDate {

	public static void main(String args[]) {
		 testTimeZone();
		 diffDateTime();
	}

	private static void diffDateTime() {
		String dateStart = "2013-02-01 09:32";
		String dateStop = "2013-02-02 11:36";
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm");
		
		DateTime d1 = null;
		DateTime d2 = null;
		
		d1 = dtf.parseDateTime(dateStart);
		d2 = dtf.parseDateTime(dateStop);
		
		Period period = new Period(d1, d2);
	    System.out.println("Difference between Days " + period.getDays());
	    System.out.println("Difference between Hours " + period.getHours());
	    System.out.println("Difference between minutes " + period.getMinutes());
	    System.out.println("Difference between seconds " + period.getSeconds());
		
	}

	private static void testTimeZone() {
		double pvWattsPerDay = 0.0d;
		 
		 DateTimeZone dtz = DateTimeZone.forTimeZone(TimeZone.getTimeZone("EST"));

		    DateTime dt = new DateTime(dtz);
		    
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(dt.toDate());
		    

		    System.out.println("number of days " + Calendar.getInstance().getActualMaximum(cal.DAY_OF_MONTH));
		    
		    pvWattsPerDay = (1) / Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

		    System.out.println(pvWattsPerDay);
	}
}
