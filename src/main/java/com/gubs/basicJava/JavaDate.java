package com.gubs.basicJava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Weeks;
import org.joda.time.Years;

public class JavaDate {

	public static void main(String args[]) {
		 testTimeZone();
    try {
      diffDateTime();
      weekDays();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	}

  private static void weekDays() {
    String dateStart = "2014-02-10 09:32";
    String dateStop = "2014-05-04 11:36";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date d1 = null, d2 = null;
    try {
      d1 = sdf.parse(dateStart);
      d2 = sdf.parse(dateStop);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    DateTime dt1 = new DateTime(d1);
    DateTime dt2 = new DateTime(d2);
    int weekDays = 0;
    while (dt1.isBefore(dt2)) {
      // dayofWeek return int based on the days
      if (dt1.getDayOfWeek() <= 5) {
        weekDays = weekDays + 1;
      }
      dt1 = dt1.plusDays(1);

      System.out.println("Date " + dt1.toString());
    }

    System.out.println("Week Days " + weekDays);

  }

  private static void diffDateTime() throws ParseException {
    String dateStart = "2014-02-10 09:32";
    String dateStop = "2014-05-04 11:36";
		
    // DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date d1 = sdf.parse(dateStart);
    Date d2 = sdf.parse(dateStop);
		
    DateTime dt1 = new DateTime(d1);
    DateTime dt2 = new DateTime(d2);
 		
		
    /*
     * Period period = new Period(d1, d2); System.out.println("Difference between Days " + period.getDays());
     * System.out.println("Difference between Hours " + period.getHours());
     * System.out.println("Difference between minutes " + period.getMinutes());
     * System.out.println("Difference between seconds " + period.getSeconds());
     */
    System.out.println("Years between " + Years.yearsBetween(dt1, dt2).getYears());

    System.out.println("Days between " + Days.daysBetween(dt1, dt2).getDays());

    System.out.println("Hours between " + Hours.hoursBetween(dt1, dt2).getHours());
    
    System.out.println("Weeks between " + Weeks.weeksBetween(dt1, dt2).getWeeks());
		
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
