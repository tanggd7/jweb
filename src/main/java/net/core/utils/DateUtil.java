package net.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author 汤国栋
 * @version 1.0
 * @date 2016年7月28日 上午9:18:30
 */
public final class DateUtil {

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化
     *
     * @param date
     * @param formatString
     * @return
     * @author 汤国栋
     * @date 2016年12月13日 上午9:48:49
     */
    private static String format(Date date, String formatString) {
        try {
            return new SimpleDateFormat(formatString).format(date);
        } catch (Exception e) {
            throw new RuntimeException("日期转换字符串格式出错", e);
        }
    }

    /**
     * 日期格式化（年-月-日）
     *
     * @param date
     * @return
     * @author 汤国栋
     * @date 2016年12月13日 上午9:50:39
     */
    public static String formatDay(Date date) {
        return format(date, DateUtil.DATE_FORMAT);
    }

    /**
     * 日期格式化（年-月-日 时:分:秒）
     *
     * @param date
     * @return
     * @author 汤国栋
     * @date 2016年12月13日 上午9:52:04
     */
    public static String formatTime(Date date) {
        return format(date, DateUtil.DATE_TIME_FORMAT);
    }

    /**
     * 将字符串转为Date
     *
     * @param StringDate
     * @param formatString
     * @return
     * @throws ParseException
     * @author 汤国栋
     * @date 2016年12月26日 下午1:52:39
     */
    private static Date parseDate(String StringDate, String formatString) {
        try {
            return new SimpleDateFormat(formatString).parse(StringDate);
        } catch (Exception e) {
            throw new RuntimeException("字符串转换日期出错", e);
        }
    }

    /**
     * 将字符串转为Date
     *
     * @param StringDate
     * @return
     * @throws ParseException
     * @author 汤国栋
     * @date 2016年12月13日 上午9:55:02
     */
    public static Date parseDate(String StringDate) {
        return StringDate == null || StringDate == "" || StringDate.length() <= 0 ? null : parseDate(DateUtil.DATE_FORMAT, StringDate);
    }

    /**
     * 将字符串转为DateTime
     *
     * @param StringDate
     * @return
     * @throws ParseException
     * @author 汤国栋
     * @date 2016年12月13日 上午9:56:56
     */
    public static Date parseDateTime(String StringDate) {
        return StringDate == null || StringDate == "" || StringDate.length() <= 0 ? null : parseDate(DateUtil.DATE_TIME_FORMAT, StringDate);
    }

    /**
     * 获取当前时间
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:08:21
     */
    public static Date getDateTime() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 获取当前日期（年-月-日）
     *
     * @return
     * @author 汤国栋
     * @date 2016年7月6日 上午9:21:48
     */
    public static String getCurrentDate() {
        return formatDay(getDateTime());
    }

    /**
     * 获取当前时间（年-月-日 时:分:秒）
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:14:33
     */
    public static String getCurrentDateTime() {
        return formatTime(getDateTime());
    }

    /**
     * 获取当前年份
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:20:03
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:20:30
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取今天在本年的第几天
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:21:03
     */
    public static int getDayOfYear() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取今天在本月的第几天
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:22:04
     */
    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取今天在本周的第几天
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:22:44
     */
    public static int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定年第一天（年-月-日 00:00:00）
     *
     * @param year
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午3:33:14
     */
    public static Date getFirstDateOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        return calendar.getTime();
    }

    /**
     * 获取指定年的最后一天（年-月-日 23:59:59）
     *
     * @param year
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午3:36:27
     */
    public static Date getLastDateOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        return calendar.getTime();
    }

    /**
     * 获取本年的第一天（年-月-日 00:00:00）
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午3:03:28
     */
    public static Date getFirstDateOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        return calendar.getTime();
    }

    /**
     * 获取本年的最后一天（年-月-日 23:59:59）
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午3:16:17
     */
    public static Date getLastDateOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        return calendar.getTime();
    }

    /**
     * 获取指定年月的第一天（年-月-日 00:00:00）
     *
     * @param year
     * @param month
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午3:44:14
     */
    public static Date getFirstDateOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取指定年月的最后一天（年-月-日 23:59:59）
     *
     * @param year
     * @param month
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午3:45:17
     */
    public static Date getLastDateOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取本月的第一天（年-月-日 00:00:00）
     *
     * @return
     * @author 汤国栋
     * @date 2016年7月28日 上午9:51:08
     */
    public static Date getFirstDateOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取本月的最后一天（年-月-日 23:59:59）
     *
     * @return
     * @author 汤国栋
     * @date 2016年7月28日 上午10:03:00
     */
    public static Date getLastDateOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取本周的第一天（星期一：年-月-日 00:00:00）
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午2:41:11
     */
    public static Date getFirstDateOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获取本周的最后一天（星期一：年-月-日 23:59:59）
     *
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午3:23:42
     */
    public static Date getLastDateOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return calendar.getTime();
    }

}
