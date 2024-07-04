/**
 * Author: Md Jamil Ashraf, Md-Naushad
 * Created Date: Jan 21, 2020
 * Copyright © 2019, Ultimatetek Solutions. All rights reserved.
 */
package com.ultimatetek.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /*
	 * Returns Current Date set In weblogic server in Stirng
	 *
	 * @return Current Date in dd/MM/yyyy Format
     */

 /*
	 * public static Date subtractDaysFromTodaysDate(int days) {
	 * 
	 * DateTime futureDate = new DateTime();
	 * 
	 * futureDate = futureDate.plusDays(-days);
	 * 
	 * // LocalDate firstDate = first.toLocalDate(); // LocalDate secondDate =
	 * second.toLocalDate(); // // check whether the future date has passed current
	 * date. return futureDate.toDate(); }
     */

 /*
	 * public static boolean isDateExpiredInMonths(Date givenDate, int months) {
	 * 
	 * // DateTime first=new DateTime(givenDate.getTime()); // Create a joda time
	 * instance DateTime futureDate = new DateTime(givenDate.getTime()); // add
	 * months to the jodatime instance futureDate = futureDate.plusMonths(months);
	 * 
	 * // LocalDate firstDate = first.toLocalDate(); // LocalDate secondDate =
	 * second.toLocalDate(); // // check whether the future date has passed current
	 * date. return futureDate.isBeforeNow(); }
     */
    public static int getCurrentMonth() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    public static int getYearFromDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static String getTodayDateInString() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(calendar.getTime());

    }

    public static String getCurrentTimeinString() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(calendar.getTime());
    }

    public static String getTimeFromDate(Date date) {
        if (date == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Date getTimeFromDateInTime(Date date) {
        if (date == null) {
            return null;
        }
        String dateFormat1 = new SimpleDateFormat("HH:mm:ss").format(date);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            date = dateFormat.parse(dateFormat1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Timestamp getDateinTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());

    }

    public static String getFormattedTimeStamp(Timestamp timestamp) {
        String str = new SimpleDateFormat("dd/MM/yyyy").format(timestamp);
        return str;
    }

    public static Date getFormattedDate(String dateStr) {

        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date dateJava = null;
        try {
            dateJava = formatter.parse(dateStr);

        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }

        return dateJava;
    }

    public static String getFormattedDate(Date date) {

        if (date == null) {
            return null;
        }
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);

    }

    public static String getFormattedDateForMysql(Date date) {

        if (date == null) {
            return null;
        }
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static String getFormattedDateForOracle(Date date) {

        if (date == null) {
            return null;
        }
        Format formatter;
        formatter = new SimpleDateFormat("dd-MMM-yy");
        return formatter.format(date);
    }

    public static boolean isCurrentTimeBetween(String start, String end) {

        try {
            String currentTime = DateUtils.getCurrentTimeinString();

            String startHourString = start.substring(0, 2);
            String startMinuteString = start.substring(3, 5);
            String endHourString = end.substring(0, 2);
            String endMinuteString = end.substring(3, 5);
            String currentHourString = currentTime.substring(0, 2);
            String currentMinuteString = currentTime.substring(3, 5);

            int startHour = Integer.parseInt(startHourString);
            int startMinute = Integer.parseInt(startMinuteString);
            int endHour = Integer.parseInt(endHourString);
            int endMinute = Integer.parseInt(endMinuteString);
            int currentHour = Integer.parseInt(currentHourString);
            int currentMinute = Integer.parseInt(currentMinuteString);
//	                  if current hour is greater than startHour And Less than endhour Return true
//	                    No need of checking for time...
            if (currentHour > startHour && currentHour < endHour) {
                return true;
            }
//	                    If either start Hour or End Hour is equal than check for minitues and return the condition.
            if (currentHour == startHour || currentHour == endHour) {
                boolean startMinFlag = false, endMinFlag = false;
                if (currentHour == startHour) {
                    if (currentMinute > startMinute) {
                        startMinFlag = true;
                    }
                }
                if (currentHour == endHour) {
                    if (currentMinute < endMinute) {
                        endMinFlag = true;
                    }

                }
                return (startMinFlag || endMinFlag);

            }

//	                Date startTime = f.parse(start);
//	                Date endTime = f.parse(end);
//	                Date currentTime = f.parse (DateUtils.getCurrentTimeinString());
//	                
//	                if(f.format(startTime).compareTo(f.format(currentTime)) <= 0 && f.format(endTime).compareTo(f.format(currentTime)) >= 0){
//	                     return true;
//	                }
//	                if (f.format(startTime) && currentTime.before(endTime)) {
//	                   
//	                }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    public static Date getDateOneMontAgo() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        Date newDate = cal.getTime();
        return newDate;
    }

    public static Date getDateOneYearAgo() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        Date newDate = cal.getTime();
        return newDate;
    }

    public static Date getDateTenYearAgo() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -10);
        Date newDate = cal.getTime();
        return newDate;
    }

    public static Date getDateOneWeekAgo() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date newDate = cal.getTime();
        return newDate;
    }

    /*
	 * @param Date in String according to Georgerian calendar in dd/MM/yyyy Format
	 * 
	 * @return Date in Hijri for Given Date
	 *
     */
 /*
	 * Returns Current Date set In weblogic server in java Date Format
	 *
	 * @return Current Date in dd/MM/yyyy Format
     */
    public static Date getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return calendar.getTime();

    }

    public static Date getNextDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return tomorrow;

    }

    public static int getCurrentYearInNumber() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static double DifferenceInMonths(Date date1, Date date2) {
        return DifferenceInYears(date1, date2) * 12;
    }

    public static double DifferenceInYears(Date date1, Date date2) {
        double days = DifferenceInDays(date1, date2);
        return days / 365.2425;
    }

    public static double DifferenceInDays(Date date1, Date date2) {
        return DifferenceInHours(date1, date2) / 24.0;
    }

    public static double DifferenceInHours(Date date1, Date date2) {
        return DifferenceInMinutes(date1, date2) / 60.0;
    }

    public static double DifferenceInMinutes(Date date1, Date date2) {
        return DifferenceInSeconds(date1, date2) / 60.0;
    }

    public static double DifferenceInSeconds(Date date1, Date date2) {
        return DifferenceInMilliseconds(date1, date2) / 1000.0;
    }

    private static double DifferenceInMilliseconds(Date date1, Date date2) {
        return Math.abs(GetTimeInMilliseconds(date1) - GetTimeInMilliseconds(date2));
    }

    public static long GetTimeInMilliseconds(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis() + cal.getTimeZone().getOffset(cal.getTimeInMillis());
    }

    public static Date getDateAYearPlus() {
        Calendar today_plus_year = Calendar.getInstance();
        today_plus_year.add(Calendar.YEAR, 1);
        return today_plus_year.getTime();
    }

    public static Date getWeekStartDate() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        return calendar.getTime();
    }

    public static Date getWeekEndDate() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static String getMonthStartDate() {
        Date start = getMonthDate("START");
        return getFormattedDateForMysql(start);
    }

    public static String getMonthEndDate() {
        Date end = getMonthDate("END");
        return getFormattedDateForMysql(end);
    }

    /**
     * @param filter START for start date of month e.g. Nov 01, 2013 END for end
     * date of month e.g. Nov 30, 2013
     * @return
     */
    public static Date getMonthDate(String filter) {
        // String MMM_DD_COMMA_YYYY = "MMM dd, yyyy";
        // SimpleDateFormat sdf = new SimpleDateFormat(MMM_DD_COMMA_YYYY);
        // sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        // sdf.format(GregorianCalendar.getInstance().getTime());

        Calendar cal = GregorianCalendar.getInstance();
        int date = cal.getActualMinimum(Calendar.DATE);
        if ("END".equalsIgnoreCase(filter)) {
            date = cal.getActualMaximum(Calendar.DATE);
        }
        cal.set(Calendar.DATE, date);
        cal.getTime();
//	        System.out.println(" " + result  );

        return cal.getTime();
    }

    public static Date getFirstDateOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.MONTH, 0);
        calendar.set(calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getLastDateOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.MONTH, 11);
        calendar.set(calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }

    public static Date getFirstDayOfQuarter() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date getLastDayOfQuarter() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3 + 2);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static float daysBetween(Date from, Date to) {
        float days = 0;
        if (from != null && to != null) {
            long difference = to.getTime() - from.getTime();
            days = (difference / (1000 * 60 * 60 * 24));
            System.out.println("days=" + days);
        }
        return days;

    }

    public static String getFormattedTime(String odometerTime) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(odometerTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static Date convertTimeToDate(String time) {
        // DateFormat df6 = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy");
        Date date = null;
        try {
            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date incrementDate(Date oldDate, Integer days) {
        //Given Date in String format
        Calendar c = Calendar.getInstance();
        try {
            //Setting the date to the given date
            c.setTime(oldDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, days - 1);
        //Date after adding the days to the given date
        return c.getTime();
    }

    public static Date incrementDateNew(Date oldDate, Integer days) {
        //Given Date in String format
        Calendar c = Calendar.getInstance();
        try {
            //Setting the date to the given date
            c.setTime(oldDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, days);
        //Date after adding the days to the given date
        return c.getTime();
    }

    public static Date getFormattedDateTime(String time) {

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String getFirstDayOfCurrentWeek() {
        // set the date
        Calendar cal = Calendar.getInstance();
        // "calculate" the start date of the week
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK,
                first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(first.getTime());
    }

    public static String getLastDayOfCurrentWeek() {
        // set the date
        Calendar cal = Calendar.getInstance();
        // "calculate" the start date of the week
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK,
                first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(last.getTime());
    }

    /*public static void main(String args[]) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 5);
		daysBetween(new Date(), c.getTime());
		getTimeFromDateInTime(new Date());
	}*/
    public static String convertDateToStringFormat(Date date, String strDateFormat) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
        //System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }
}
