package com.ef;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtils {

    private static final SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final SimpleDateFormat dateArgumentFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");


    public static Date parseLogDate(String date) throws ParseException {
        return logDateFormat.parse(date);
    }


    public static Date parseDateArgument(String date) throws ParseException {
        return dateArgumentFormat.parse(date);
    }

    public static Date getDateAfterDuration(String startDate, String duration) throws ParseException {

        Date fromDate = dateArgumentFormat.parse(startDate);
        int durationInHours = 0;

        if ("hourly".equalsIgnoreCase(duration)) {
            durationInHours = 1;
        } else if ("daily".equalsIgnoreCase(duration)) {
            durationInHours = 24;
        } else {
            throw new IllegalArgumentException("Invalid duration: " + duration);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.HOUR_OF_DAY, durationInHours);
        Date toDate = calendar.getTime();
        return toDate;
    }

}
