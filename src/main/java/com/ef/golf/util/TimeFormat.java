package com.ef.golf.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormat {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORT_DATETIME_FORMAT = "MM-dd HH:mm";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String TIME_TO_MINUTE_FORMAT = "HH:mm";
    private static final String[] DAY_OF_WEEK = new String[] {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static String formatDatetime(Date date) {
        if (date == null) return null;
        return DateFormatUtils.format(date, DATETIME_FORMAT);
    }

    public static String formatShortDatetime(Date date) {
        if (date == null) return null;
        return DateFormatUtils.format(date, SHORT_DATETIME_FORMAT);
    }

    public static String formatDate(Date date) {
        if (date == null) return null;
        return DateFormatUtils.format(date, DATE_FORMAT);
    }

    public static String formatDate(Date date,String format) {
        if (date == null) return null;
        return DateFormatUtils.format(date, format);
    }

    public static Date formatDateStr(String dateStr) throws ParseException {
        if(StringUtils.isEmpty(dateStr))  return null;
        return new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
    }

    public static String formatTime(Date date) {
        if (date == null) return null;
        return DateFormatUtils.format(date, TIME_FORMAT);
    }

    public static String formatTimeToMinute(Date date) {
        if (date == null) return null;
        return DateFormatUtils.format(date, TIME_TO_MINUTE_FORMAT);
    }

    public static String formatFriendlyTime(Date date) {
        if (date == null) return null;
        Calendar current = Calendar.getInstance();
        Calendar update = Calendar.getInstance();
        update.setTime(date);
        int yearDiff = current.get(Calendar.YEAR) - update.get(Calendar.YEAR);
        int monthDiff = current.get(Calendar.MONTH) - update.get(Calendar.MONTH);
        int dayDiff = current.get(Calendar.DAY_OF_YEAR) - update.get(Calendar.DAY_OF_YEAR);
        long secondDiff = (current.getTime().getTime() - update.getTime().getTime()) / 1000;

        if (secondDiff <= 60) {
            return "刚刚";
        }
        if (secondDiff <= 300) {
            return String.format("%d分钟前", secondDiff / 60);
        }
        if (yearDiff == 0 && monthDiff == 0 && dayDiff == 0) {
            return DateFormatUtils.format(date, "HH:mm");
        }
        if (yearDiff == 0 && monthDiff == 0 && dayDiff == 1) {
            return "昨天";
        }
        if (yearDiff == 0 && monthDiff == 0 && dayDiff < 7) {
            return DAY_OF_WEEK[update.get(Calendar.DAY_OF_WEEK) - 1];
        }
        return DateFormatUtils.format(date, "yy/MM/dd");
    }
}
