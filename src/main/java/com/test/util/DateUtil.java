package com.test.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zzh
 * @create 2019-08-24 14:09
 * @desc 时间工具类
 */
public class DateUtil {

    public static String pattern = "yyyy-MM-dd";
    public static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

    public static DateTimeFormatter getDateTimeFormatter() {
        return dateFormatter;
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
//        Date currDate = new Date();
        return currDate;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        String dateString = formatter.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyyMMddHHmmss
     */
    public static String getStringAllDate2() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyyMMddHHmmss
     */
    public static String getStringAllDate1() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    // 分钟取5，往上
    public static String getStringAllDate5Min() {
        Date date = new Date(); //这里得到需要转换的北京时间
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, -8);//得到yyyy-MM-dd HH:mm:ss格式的世界时间
        int minute = ca.get(Calendar.MINUTE);
//        System.out.println("--minute--" + minute);
//        minute = Math.round(minute/5*10);//计算10的整数分钟
        minute = (int) Math.floor(minute / 5) * 5;
//        System.out.println("--minute 2--" + minute);
        minute -= 5;
        ca.set(Calendar.MINUTE, minute);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String airtime = sdf.format(ca.getTime());
//        System.out.println("--airtime 1--" + airtime);
//        airtime = airtime.substring(0, 10) + "T" + airtime.substring(11);//得到需要格式的世界时间
//        System.out.println("--airtime 2--" + airtime);
        return airtime;
    }

    // 分钟取10，往上
    public static String getStringAllDate10Min() {
        Date date = new Date(); //这里得到需要转换的北京时间
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, -8);//得到yyyy-MM-dd HH:mm:ss格式的世界时间
        int minute = ca.get(Calendar.MINUTE);
//        System.out.println("--minute--" + minute);
//        minute = Math.round(minute/5*10);//计算10的整数分钟
        minute = (int) Math.floor(minute / 5) * 5;
//        System.out.println("--minute 2--" + minute);
        minute -= 10;
        ca.set(Calendar.MINUTE, minute);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String airtime = sdf.format(ca.getTime());
//        System.out.println("--airtime 1--" + airtime);
//        airtime = airtime.substring(0, 10) + "T" + airtime.substring(11);//得到需要格式的世界时间
//        System.out.println("--airtime 2--" + airtime);
        return airtime;
    }

    // 小时取整，往上
    public static String getStringAllDate1Hour() {
        Date date = new Date(); //这里得到需要转换的北京时间
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, -8);//得到yyyy-MM-dd HH:mm:ss格式的世界时间
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String airtime = sdf.format(ca.getTime());
        return airtime;
    }

    // 小时取整，往上
    public static String getStringAllDate2Hour() {
        Date date = new Date(); //这里得到需要转换的北京时间
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, -9);//得到yyyy-MM-dd HH:mm:ss格式的世界时间
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String airtime = sdf.format(ca.getTime());
        return airtime;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort1() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    public static String getTimeShort() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒
        String dateString = "" + hour + minute + second;
        return dateString;
    }

    public void getTimeByCalendar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH);//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
        System.out.println("现在的时间是：公元" + year + "年" + month + "月" + day + "日      " + hour + "时" + minute + "分" + second + "秒       星期" + WeekOfYear);
    }
}
