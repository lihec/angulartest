package com.spbt.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间处理工具类
 * 
 * @author lihe 2013-7-4 下午5:20:50
 * @see
 * @since
 */
public final class DateUtil {
    /**记录日志*/
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 时间格式
     */
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String DATE_FORMAT_SHORT = "yyyyMMdd";

    public static final String DATE_FORMAT_SHORT_TWO = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

    /**
     * 私有构造
     * 
     * @author 李贺[of253]
     * @date 2013-12-6 下午4:53:01
     */
    private DateUtil() {
    }

    /**
     * 获得当前时间，格式 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getCurrentDate() {
        return getCurrentDate(TIME_FORMAT);
    }

    /**
     * 获得当前时间，格式自定义
     * 
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        Calendar day = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(day.getTime());
    }

    /**
     * 获得昨天时间，格式自定义
     * 
     * @param format
     * @return
     */
    public static String getYesterdayDate(String format) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(day.getTime());
    }

    /**
     * 获取每个月的第一天时间
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(c.getTime());
    }

    /**
     * 获取每个月的最后一天时间
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(c.getTime());
    }

    /**
     * 时间加减天
     *
     * @param day
     * @return
     */
    public static Date add(Date time,int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    public static String getLastDayOfMonth(int year, int month, String format) {
        Calendar c = new GregorianCalendar();
        c.set(year, month - 1, 1, 0, 0, 0);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(c.getTime());
    }

    /**
     * 修复代码质量加的注释，这个方法很绕- -
     * 
     * @author lihe 2013-7-4 下午5:21:33
     * @param date
     * @param format
     * @return
     * @see
     * @since
     */
    public static String formatData(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            logger.error(e.toString(), e);
            return "";
        }
    }

    /**
     * 修复代码质量加的注释，这个方法很绕- -
     *
     * @author lihe 2013-7-4 下午5:21:33
     * @param date
     * @param format
     * @return
     * @see
     * @since
     */
    public static String formatData(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return "";
        }
    }

    /**
     * 用于图表中日期转换
     * @param date
     * @param formatDest
     * @param formatOrig
     * @return
     */
    public static String formatData(String date, String formatDest, String formatOrig) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatDest);
            return sdf.format(new SimpleDateFormat(formatOrig).parse(date));
        } catch (ParseException e) {
            logger.error(e.toString(), e);
            return "";
        }
    }

    /**
     * 得到改日期属于一年的第几周
     * @param dateTime
     * @param format
     * @return
     */
    public static int getInfoFromCal(String dateTime, String format, int CalendarType) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(date);
            return calendar.get(CalendarType);
        } catch (ParseException e) {
            logger.error(e.toString(), e);
            return 0;
        }
    }

    /**
     * 两个日期相隔的天数
     * @param format
     * @return
     */
    public static long getDiffDay(String dateStart, String dateEnd, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date1 = sdf.parse(dateStart);
            Date date2 = sdf.parse(dateEnd);
            int nd = 1000*24*60*60;//一天的毫秒数
            long diff = date2.getTime() - date1.getTime();

            return diff/nd;
        } catch (ParseException e) {
            logger.error(e.toString(), e);
            return 0;
        }
    }

    /**
     * 查找某个时间段内的日期  为图表服务（如 某个时间段内 周日， 某个时间段的月末）
     * @param beginTime 开始日期 图表为yyyyMMdd
     * @param endTime
     * @return
     */
    public static List<String> getDateRangeCal(String beginTime, String endTime, String type) {
        List<String> dates = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date beginDate = sdf.parse(beginTime);
            Date endDate = sdf.parse(endTime);

            dates = new ArrayList<String>();
            if ("M".equals(type)) {
                beginDate = getLastMonthDay(beginDate);
                dates.add(sdf.format(beginDate));
            }

            Calendar calendarStrat = new GregorianCalendar();
            calendarStrat.setTime(beginDate);
            Calendar calendarEnd = new GregorianCalendar();
            calendarEnd.setTime(endDate);

            while (calendarStrat.compareTo(calendarEnd) < 0) {
                if ("W".equals(type)) {
                    calendarStrat.add(Calendar.DATE, 1); //DATE=日
                    if ((calendarStrat.get(Calendar.DAY_OF_WEEK)) == 1) {
                        dates.add(sdf.format(calendarStrat.getTime()));
                    }
                }
                if ("M".equals(type)) {
                    calendarStrat.add(Calendar.MONTH, 1);
                    dates.add(sdf.format(getLastMonthDay(calendarStrat.getTime())));
                }
            }
            return dates;
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return null;
        }
    }

    /**
     * 按照时间字符串和格式转换成Date类
     * 
     * @author lihe 2013-7-4 下午5:21:50
     * @param date
     * @param format
     * @return
     * @throws java.text.ParseException
     * @see
     * @since
     */
    public static Date getDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    /**
     * 方法描述：时间验证 创建人：fengsen 创建时间：2012-12-7 备注：时间间隔是否超过N个月
     * 
     * @param startTime
     *            起始时间
     * @param endTime
     *            终止时间
     * @param month
     *            月数
     * @return
     */
    public static boolean isOutOfMonth(String startTime, String endTime, int month) {
        try {
            Date start = getDate(startTime, TIME_FORMAT);
            Date end = getDate(endTime, TIME_FORMAT);
            if (end.getTime() < start.getTime()) {
                return true;
            }
            final long monthDay = 30L;
            final int hourOfDay = 24;
            final int minOfHour = 60;
            final int secOfmin = 60;
            long times = month * monthDay * hourOfDay * minOfHour * secOfmin;
            final int msec = 1000;
            if ((end.getTime() - start.getTime()) / msec < times) {
                return false;
            }
            return true;
        } catch (ParseException e) {
            logger.error(e.toString(), e);
            return true;
        }
    }

    public static Date getDateForTodayStart() {
        return DateUtils.truncate(new Date(), Calendar.DATE);
    }

    public static Date getDateForTodayEnd() {
        Date date = new Date();
        date = DateUtils.setHours(date, 23);
        date = DateUtils.setMinutes(date, 59);
        date = DateUtils.setSeconds(date, 59);
        return date;
    }

    /**
     * 获取上月的第一天
     * @return
     */
    public static Date getFirstDayOfPreMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取上月的最后一天
     * @return
     */
    public static Date getEndDayOfPreMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static Date getFirstMonthDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    public static Date getLastMonthDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 得到当前时间小时数
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 一天相差分钟数是否在指定范围内
     * @param time 格式HH:ss
     * @param mins
     * @return
     */
    public static boolean checkMinsInMins(String time, int mins) {
        try {
            Date d = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(d);

            Calendar calendar1 = new GregorianCalendar();
            int thour = 0;
            int tmins = 0;
            if (StringUtils.isBlank(time)) {
                return false;
            } else {
                if (time.contains(":")) {
                    String[] dd = time.split(":");
                    thour = Integer.parseInt(dd[0]);
                    tmins = Integer.parseInt(dd[1]);
                } else {
                    thour = Integer.parseInt(time);
                }
                calendar1.setTime(d);
                calendar1.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),
                        thour,tmins);
            }
            long times = (calendar1.getTimeInMillis() - calendar.getTimeInMillis())/(1000*60);
            if (times < mins && times > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("checkMinsInMins校验失败");
        }
        return false;
    }

    /**
     *
     * @param sOrdertime
     * @param eOrdertime
     * @return
     */
    public static boolean checkCurrentTimeCanOrder(String sOrdertime, String eOrdertime) {
        try {
            Date d = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);

            //订单开始时间判断
            if (StringUtils.isNotBlank(sOrdertime)) {
                int shour = 0;
                int smin = 0;
                if (sOrdertime.contains(":")) {
                    String[] dd = sOrdertime.split(":");
                    shour = Integer.parseInt(dd[0]);
                    smin = Integer.parseInt(dd[1]);
                } else {
                    shour = Integer.parseInt(sOrdertime);
                }
                if (hour < shour) {
                    return false;
                } else if (hour == shour) {
                    if (min < smin) {
                        return false;
                    }
                }
            }

            //订单结束时间判断
            if (StringUtils.isNotBlank(eOrdertime)) {
                int ehour = 0;
                int emin = 0;
                if (eOrdertime.contains(":")) {
                    String[] dd = eOrdertime.split(":");
                    ehour = Integer.parseInt(dd[0]);
                    emin = Integer.parseInt(dd[1]);
                } else {
                    ehour = Integer.parseInt(eOrdertime);
                }
                if (hour > ehour) {
                    return false;
                } else if (hour == ehour) {
                    if (min > emin) {
                        return false;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("checkCurrentTimeCanOrder校验失败");
        }
        return true;
    }

    /**
     * 校验时间格式是否正确
     * @param str
     * @param formatStr
     * @return
     */
    public static boolean isValidDate(String str, String formatStr) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    /**
     * 堂口是否已经过了下单结束时间
     * @param eOrdertime
     * @return
     */
    public static boolean checkProjectExpire(String eOrdertime) {
        try {
            Date d = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);

            //订单结束时间判断
            if (StringUtils.isNotBlank(eOrdertime)) {
                int ehour = 0;
                int emin = 0;
                if (eOrdertime.contains(":")) {
                    String[] dd = eOrdertime.split(":");
                    ehour = Integer.parseInt(dd[0]);
                    emin = Integer.parseInt(dd[1]);
                } else {
                    ehour = Integer.parseInt(eOrdertime);
                }
                if (hour > ehour) {
                    return true;
                } else if (hour == ehour) {
                    if (min > emin) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("checkProjectExpire校验失败");
        }
        return false;
    }
}
