package com.gubs.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;

public class DateUtils {

  public static final String DATE_FORMAT_EMAIL_REPORT_CREATE_DATE = "EEE, MMM d hh:mm:ss z yyyy";

  public static final String HH_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static final String DEFAULT_DATE_FORMAT_WITH_TZ = "yyyy-MM-dd HH:mm:ss zzz";

  public static final String HH_MM_FORMAT_WITH_TZ = "HH:mm zzz";

  public static final String DATE_HOUR_FORMAT = "yyyy-MM-dd H";

  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

  public static final String MONTH_DAY_DATE_FORMAT = "MMM d yyyy";

  public static final String DAY_MONTH_TIMESTAMP_FORMAT = "EEE MMM d yyyy hh:mm:ss a";

  public static final String CALENDAR_DATE_FORMAT_FROM_UI_OLD = "EEE MMM d yyyy";

  public static final String CALENDAR_DATE_FORMAT_FROM_UI = "MM/dd/yyyy";

  public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static final String DATE_TIME_FORMAT_WITH_T = "yyyy-MM-dd'T'HH:mm:ss";

  public static final String DATE_FORMAT_FOR_FILE_GENERATION = "MMddyyyy_HHmmss";

  private static SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static String[] MONTHS = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
      "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };

  public static String[] SHORT_MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
      "Dec" };

  /**
   * Returns the current day.
   * 
   * @return Day after <code>date</code>.
   */
  public static Date currentDay() {
    Calendar calendar = Calendar.getInstance();
    return new Date(calendar.getTimeInMillis());
  }

  public static int getYearsDiff(long t1, long t2) {
    DateTime dt1 = new DateTime(t1);
    DateTime dt2 = new DateTime(t2);

    return Years.yearsBetween(dt1, dt2).getYears();
  }

  public static int getDaysDiff(long t1, long t2) {
    DateTime dt1 = new DateTime(t1);
    DateTime dt2 = new DateTime(t2);

    return Days.daysBetween(dt1, dt2).getDays();
  }

  public static String getDateInFormat(String strDateFormat, Date date) {

    String formattedDate = "";
    try {
      DateFormat df = new SimpleDateFormat(strDateFormat);
      formattedDate = df.format(date);
    } catch (Exception e) {
    }

    return formattedDate;
  }

  public static String getDateInFormat(String strDateFormat, long timeInMilliseconds) {

    String formattedDate = "";
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
      formattedDate = sdf.format(new Date(timeInMilliseconds));
    } catch (Exception e) {
    }

    return formattedDate;
  }

  public static Date getDateByParsing(String strDateFormat, String date) {

    Date parseDate = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
      parseDate = sdf.parse(date);
    } catch (Exception e) {
      System.out.println("Failed to parse date " + e.getMessage());
    }
    return parseDate;
  }

  public static Date startOfDayAsDateObjectBySubmittedDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MINUTE, 0);
    return new Date(calendar.getTimeInMillis());
  }

  public static Date endOfDayAsTimestampObjectBySubmittedDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MILLISECOND, 999);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MINUTE, 59);
    return new Date(calendar.getTimeInMillis());
  }

  public static long getGMTTimeInMillis(Date currDate) {
    long gmtTimeMillis = 0;
    DateFormat estFormat = new SimpleDateFormat();
    DateFormat gmtFormat = new SimpleDateFormat();
    TimeZone gmtTime = TimeZone.getTimeZone("GMT");
    TimeZone estTime = getDefaultTimeZone();
    estFormat.setTimeZone(gmtTime);
    gmtFormat.setTimeZone(estTime);
    try {
      gmtTimeMillis = gmtFormat.parse(estFormat.format(currDate.getTime())).getTime();
      Date date = new Date(gmtTimeMillis);
      // System.out.println("EST >>>" + currDate);
      // System.out.println("GMT >>>" + date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return gmtTimeMillis;
  }

  public static long getGMTTimeInSecs(Date currDate) {
    long gmtTimeSecs = 0;
    gmtTimeSecs = getGMTTimeInMillis(currDate) / 1000;
    return gmtTimeSecs;
  }

  public static TimeZone getDefaultTimeZone() {
    return TimeZone.getTimeZone("EST");
  }

  /**
   * Method will return time rounded off to near 15 minute. It will work like Floor function in Excel for
   * 10:23 ->10:15 , for 10:01 -> 10:00
   * 
   * @param timeToRoundOff
   * @return
   */
  @SuppressWarnings({ "deprecation" })
  public static java.sql.Timestamp roundOffMinutesFloor(java.sql.Timestamp timeToRoundOff) {
    int minute = timeToRoundOff.getMinutes();
    if (minute >= 0 && minute < 15) {
      timeToRoundOff.setMinutes(0);
      timeToRoundOff.setSeconds(0);
    } else if (minute >= 15 && minute < 30) {
      timeToRoundOff.setMinutes(15);
      timeToRoundOff.setSeconds(0);
    } else if (minute >= 30 && minute < 45) {
      timeToRoundOff.setMinutes(30);
      timeToRoundOff.setSeconds(0);
    } else if (minute >= 45 && minute < 60) {
      timeToRoundOff.setMinutes(45);
      timeToRoundOff.setSeconds(0);
    }
    timeToRoundOff.setNanos(0);
    return timeToRoundOff;
  }

  /**
   * Method will return time rounded off to near 15 minute. It will work like Ceil function in Excel for
   * 10:23-> 10:30, for 10:01 -> 10:15, for 10:00 -> 10:00
   * 
   * @param timeToRoundOff
   * @return
   */
  @SuppressWarnings({ "deprecation" })
  public static java.sql.Timestamp roundOffMinutesCeiling(java.sql.Timestamp timeToRoundOff) {
    int minute = timeToRoundOff.getMinutes();
    if (minute > 0 && minute < 15) {
      timeToRoundOff.setMinutes(15);
      timeToRoundOff.setSeconds(0);
    } else if (minute > 15 && minute < 30) {
      timeToRoundOff.setMinutes(30);
      timeToRoundOff.setSeconds(0);
    } else if (minute > 30 && minute < 45) {
      timeToRoundOff.setMinutes(45);
      timeToRoundOff.setSeconds(0);
    } else if (minute > 45 && minute < 60) {
      timeToRoundOff.setHours(timeToRoundOff.getHours() + 1);
      timeToRoundOff.setMinutes(00);
      timeToRoundOff.setSeconds(0);
    }
    timeToRoundOff.setNanos(0);
    return timeToRoundOff;
  }

  /**
   * 
   * @param timeStamp
   * @param minutes
   * @return
   */
  @Deprecated
  public static java.sql.Timestamp addMinutes(java.sql.Timestamp timeStamp, int minutes) {
    timeStamp.setTime(timeStamp.getTime() + (minutes * 1000 * 60));
    return timeStamp;
  }

  /**
   * 
   * @param dt
   * @param minutes
   * @return
   */
  public static java.util.Date addMinutes(java.util.Date dt, int minutes) {
    dt.setTime(dt.getTime() + (minutes * 1000 * 60));
    return dt;
  }

  /**
   * 
   * @param timeStamp
   * @param month
   * @return
   */
  @Deprecated
  public static java.sql.Timestamp addMonth(java.sql.Timestamp timeStamp, int month) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(new Date(timeStamp.getTime()));
    c1.roll(Calendar.MONTH, month);
    timeStamp.setTime(c1.getTime().getTime());
    return timeStamp;
  }

  /**
   * 
   * @param dt
   * @param month
   * @return
   */
  public static java.util.Date addMonth(java.util.Date dt, int month) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(dt);
    c1.add(Calendar.MONTH, month);
    dt.setTime(c1.getTime().getTime());
    return dt;
  }

  public static java.sql.Timestamp subtractTime(java.sql.Timestamp timeStamp, java.sql.Time sqlTime) {

    long milliSecondsToSubtract = 0l;
    milliSecondsToSubtract = (sqlTime.getSeconds() * 1000) + (sqlTime.getMinutes() * 1000 * 60)
        + (sqlTime.getHours() * 1000 * 60 * 60);

    timeStamp.setTime(timeStamp.getTime() - milliSecondsToSubtract);
    return timeStamp;
  }

  public static boolean isSameHour(Date date1, Date date2) {
    boolean isSameHour = false;

    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();

    calendar1.setTime(date1);
    calendar2.setTime(date2);

    if ((calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
        && (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
        && (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH))
        && (calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY))) {
      isSameHour = true;

    } else {
      isSameHour = false;
    }

    return isSameHour;
  }

  public static Timestamp currentTimeStamp() {
    Calendar calendar = Calendar.getInstance();
    return new Timestamp(calendar.getTime().getTime());
  }

  public static void main(String[] args) {
    Timestamp ts1 = new Timestamp(Calendar.getInstance().getTime().getTime());
    Time t1 = new Time(0, 15, 0);
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    System.out.println(calendar.getTime());
    Timestamp ts2 = new Timestamp(calendar.getTime().getTime());

    System.out.println("Diff2:" + subtractTime(ts2, t1));
  }

  /**
   * 
   * @param dateStr
   * @param format
   * @return
   */
  public static Date convertStringToDate(String dateStr, String format) throws ParseException {
    Date result = null;
    SimpleDateFormat sdfSource = new SimpleDateFormat(HH_TIMESTAMP_FORMAT);

    if (format != null) {
      sdfSource = new SimpleDateFormat(format);
    }

    result = sdfSource.parse(dateStr);

    return result;
  }

  /**
   * 
   * @param date
   * @param format
   * @return
   */
  public static String getFormattedDateString(Date date, String format) {
    if (date == null) {
      return "";
    }
    String formattedDate = "";

    if (format == null || format.trim().equals("")) {
      formattedDate = defaultDateFormat.format(date);
    } else {
      try {
        SimpleDateFormat customDateFormat = new SimpleDateFormat(format);
        formattedDate = customDateFormat.format(date);
      } catch (Exception ex) {
        System.out.println("Error in getFormattedDateString::" + ex.getMessage());
      }
    }

    return formattedDate;

  }

  public static int getDateDifferenceWithTimeZone(Date startDate, Date endDate, String timeZone) {
    DateTime startDateTime = new DateTime(startDate, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    DateTime endDateTime = new DateTime(endDate, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    return Days.daysBetween(startDateTime, endDateTime).getDays();
  }

  public static int getDateDifferenceWithTimeZoneForTimeInMillis(long startDateInMillis, long endDateInMillis,
      String timeZone) {
    DateTime startDateTime = new DateTime(startDateInMillis, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    DateTime endDateTime = new DateTime(endDateInMillis, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    return Days.daysBetween(startDateTime, endDateTime).getDays();
  }

  public static Long addDaysWithTimeZone(Date date, int noOfDays, String timeZone) {
    DateTime dateTime = new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0,
        0);
    return dateTime.plusDays(noOfDays).getMillis();
  }

  public static Long addDaysWithTimeZone(long date, int noOfDays, String timeZone) {
    DateTime dateTime = new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0,
        0);
    return dateTime.plusDays(noOfDays).getMillis();
  }

  public static Long addDaysToTimeInMillisWithTimeZone(long timeInMillis, int noOfDays, String timeZone) {
    DateTime dateTime = new DateTime(timeInMillis, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(
        0, 0, 0, 0);
    return dateTime.plusDays(noOfDays).getMillis();
  }

  public static long startOfCurrentDayInMillisWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0).getMillis();
  }

  public static long startOfDateInMillisWithTimezone(Date date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .getMillis();
  }

  public static long startOfDateInMillisWithTimezone(long date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .getMillis();
  }

  public static Date startOfDateWithTimezone(Date date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0).toDate();
  }

  public static Date startOfDateWithTimezone(String date, String format, String timeZone) {
    DateTime dt = DateTimeFormat.forPattern(format).withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))
        .parseDateTime(date).withTime(0, 0, 0, 0);

    return new DateTime(dt, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0).toDate();
  }

  public static Date endOfDateWithTimezone(String date, String format, String timeZone) {
    DateTime dt = DateTimeFormat.forPattern(format).withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))
        .parseDateTime(date).withTime(23, 59, 59, 999);

    return new DateTime(dt, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(23, 59, 59, 999)
        .toDate();
  }

  public static long startOfDateInMillisFromTimestampWithTimezone(long timestamp, String timeZone) {
    return new DateTime(timestamp, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .getMillis();
  }

  public static long startOfNextDateInMillisFromTimestampWithTimezone(long timestamp, String timeZone) {
    return new DateTime(timestamp, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusDays(1).getMillis();
  }

  public static long endOfCurrentDayInMillisWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(23, 59, 59, 999).getMillis();
  }

  public static long endOfYesterdayInMillisWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(23, 59, 59, 999)
        .plusDays(-1).getMillis();
  }

  public static long endOfDateInMillisWithTimezone(Date date, String timeZone) {
    return new DateTime(date.getTime(), DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(23, 59, 59,
        999).getMillis();
  }

  public static Date endOfDateWithTimezone(Date date, String timeZone) {
    return new DateTime(date.getTime(), DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(23, 59, 59,
        999).toDate();
  }

  public static long endOfDateInMillisFromTimestampWithTimezone(long timestamp, String timeZone) {
    return new DateTime(timestamp, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(23, 59, 59, 999)
        .getMillis();
  }

  public static long endOfMonthInMillisFromTimestampWithTimezone(long timestamp, String timeZone) {
    return new DateTime(startOfMonthInMillisWithTimezone(timestamp, timeZone), DateTimeZone.forTimeZone(TimeZone
        .getTimeZone(timeZone))).plusMonths(1).getMillis();
  }

  public static Date getDateFromEpochTimeInMillisWithTimezone(long timeInMillis, String timeZone) {
    return new DateTime(timeInMillis, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).toDate();
  }

  public static Date getDateFromEpochTimeInMillisWithUTCTimezone(long timeInMillis) {
    return new DateTime(timeInMillis, DateTimeZone.UTC).toDate();
  }

  public static long startOfCurrentMonthInMillisWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .withDayOfMonth(1).getMillis();
  }

  public static DateTime startOfCurrentMonthAsDateTimeWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .withDayOfMonth(1);
  }

  public static int getCurrentMonthIndicatorWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).getMonthOfYear();
  }

  public static DateTime startOfPreviousMonthAsDateTimeWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .withDayOfMonth(1).minusMonths(1);
  }

  public static long startOfMonthInMillisWithTimezone(Date date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .withDayOfMonth(1).getMillis();
  }

  public static long startOfYearInMillisWithTimezone(Date date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .withDayOfYear(1).getMillis();
  }

  public static long startOfMonthInMillisWithTimezone(Long date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .withDayOfMonth(1).getMillis();
  }

  public static long startOfMonthInMillisWithTimezone(int monthIndicator, String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withMonthOfYear(monthIndicator)
        .withDayOfMonth(1).withTime(0, 0, 0, 0).getMillis();
  }

  public static long startOfCurrentYearInMillisWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0).withDayOfYear(1)
        .getMillis();
  }

  public static DateTime startOfCurrentYearAsDateTimeWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0).withDayOfYear(1);
  }

  public static long startOfYearInMillisWithTimezone(Long date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .withDayOfYear(1).getMillis();
  }

  public static long addMonthInMillisWithTimezone(Date date, String timeZone, int month) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusMonths(month).getMillis();
  }

  public static long addHourInMillisWithTimezone(Date date, String timeZone, int hour) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusHours(hour).getMillis();
  }

  public static long addHourInMillisWithTimezone(Long date, String timeZone, int hour) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusHours(hour).getMillis();
  }

  public static long addMinutesInMillisWithTimezone(Long date, String timeZone, int minutes) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusMinutes(minutes).getMillis();
  }

  public static Date addHourToDateInMillisWithTimezone(Long date, String timeZone, int hour) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusHours(hour).toDate();
  }

  public static long addYearInMillisWithTimezone(Date date, String timeZone, int year) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusYears(year).getMillis();
  }

  public static long addMonthInMillisWithTimezone(Long date, String timeZone, int month) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusMonths(month).getMillis();
  }

  public static long addYearInMillisWithTimezone(Long date, String timeZone, int year) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .plusYears(year).getMillis();
  }

  public static long getReduceMonthInMillisWithTimezone(Date date, String timeZone, int month) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .minusMonths(month).getMillis();
  }

  public static long getReduceYearInMillisWithTimezone(Date date, String timeZone, int year) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .minusYears(year).getMillis();
  }

  public static long getReduceMonthInMillisWithTimezone(Long date, String timeZone, int month) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .minusMonths(month).getMillis();
  }

  public static long getReduceYearInMillisWithTimezone(Long date, String timeZone, int year) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .minusYears(year).getMillis();
  }

  public static long getReduceDayInMillisWithTimezone(Date date, String timeZone, int month) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .minusDays(month).getMillis();
  }

  public static long getReduceDayInMillisWithTimezone(Long date, String timeZone, int year) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withTime(0, 0, 0, 0)
        .minusDays(year).getMillis();
  }

  public static int getHourWithTimezone(Long date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).getHourOfDay();
  }

  public static int getHourWithTimezone(Date date, String timeZone) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).getHourOfDay();
  }

  public static String getFormattedDateWithTimezone(Date date, String timeZone, String formatStr) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).toString(formatStr);
  }

  public static String getFormattedDateWithTimezone(Long date, String timeZone, String formatStr) {
    return new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).toString(formatStr);
  }

  public static Date getDateByTimezone(Date date, String timeZone, String formatStr) throws ParseException {
    String dateTime = new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).toString(formatStr);
    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
    Date dateObj = sdf.parse(dateTime);
    return dateObj;
  }

  public static Date getDateByTimezone(Date date, String timeZone) throws ParseException {
    DateTime dateTime = new DateTime(date, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    Date dateObj = new Date(dateTime.getMillis());

    return dateObj;
  }

  public static boolean isTimeInMillisOnSameDayWithTimezone(long timeOne, long timeTwo, String timeZone) {
    DateTime firstDateTime = new DateTime(timeOne, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    DateTime secondDateTime = new DateTime(timeTwo, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
    return (firstDateTime.toDateMidnight()).isEqual(secondDateTime.toDateMidnight());
  }

  public static long moveTimeZoneRetainTime(long time, String timeZone) {
    return new DateTime(time).withZoneRetainFields(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))
        .getMillis();
  }

  public static long moveTimeZoneRetainTime(Date time, String timeZone) {
    return new DateTime(time).withZoneRetainFields(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)))
        .getMillis();
  }

  public static int getMonthOfYearFromTimestampWithTimeZone(long timestamp, String timeZone) {
    return new DateTime(timestamp, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).getMonthOfYear();
  }

  public static int getNoOfDaysInMonthFromTimestampWithTimeZone(long timestamp, String timeZone) {
    return new DateTime(timestamp, DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).dayOfMonth()
        .getMaximumValue();
  }

  public static long getMillisForTimeAndDateWithTimeZone(Long date, String timeZone, int hour, int min, int sec,
      int millis, int year, int month, int day) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).withDate(year, month, day)
        .withTime(hour, min, sec, millis).getMillis();
  }

  public static long getCurrentTimeInMillisWithTimezone(String timeZone) {
    return new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone))).getMillis();
  }

  public static long getTimeInMillisTillEndOfDay(String timeZone) {
    return endOfDateInMillisFromTimestampWithTimezone(getCurrentTimeInMillisWithTimezone(timeZone), timeZone)
        - getCurrentTimeInMillisWithTimezone(timeZone);
  }

  public static boolean isDayLightSavingTime(Date date) {
    TimeZone tz = TimeZone.getDefault();
    boolean result = tz.inDaylightTime(date);
    return result;
  }
}
