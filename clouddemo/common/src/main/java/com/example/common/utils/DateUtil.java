package com.example.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 说明：日期处理
 * 创建人：QQ2751682847
 * 修改时间：2015年11月24日
 */
public class DateUtil {

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyyMM");
    private final static SimpleDateFormat sdfMonthTwo = new SimpleDateFormat("yyyy-MM");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
    private final static SimpleDateFormat sdfDates = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 获取YYYY格式
     * @return
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取YYYY格式
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYYMM格式
     * @return
     */
    public static String getMonth() {
        return sdfMonth.format(new Date());
    }

    /**
     * 获取YYYY-MM格式
     * @return
     */
    public static String getMonthTwo() {
        return sdfMonthTwo.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     * @return
     */
    public static String getDays(Date date) {
        return sdfDays.format(date);
    }

    /**
     * 获取YYYY-MM-DD格式
     * @return String
     */
    public static String getDay(Date date) {
        if (date == null) {
            return "";
        }
        return sdfDay.format(date);
    }

    /**
     * 获取YYYY/MM/DD格式
     * @return
     */
    public static String getDates(Date date) {
        try {
            return sdfDates.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 获取YYYY-MM格式
     * @return 月份
     */
    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较 ， 如果s > = e 返回true 否则返回false)
     * @author fh
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    public static boolean compareDate(Date s, Date e) {
        if (s == null && e != null) {
            return false;
        }
        if (e == null && s != null) {
            return true;
        }
        return s.getTime() >= e.getTime();
    }

    public static boolean compareDateMore(String s, String e) {
        if (StringUtils.isEmpty(e) && StringUtils.isNotEmpty(s)) {
            return true;
        }
        if (StringUtils.isEmpty(e) && StringUtils.isEmpty(s)) {
            return false;
        }
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() > fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     * @return
     */
    public static Date fomatDate(String date, String fmtString) {
        DateFormat fmt = new SimpleDateFormat(fmtString);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     * @return
     */
    public static boolean isValidDate(String s, String formatString) {
        DateFormat fmt = new SimpleDateFormat(formatString);
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //long aa=0;
            int years =
                    (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days, String fmtString) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(fmtString);
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后的日期
     * @param
     * @return
     */
    public static String getAfterMinutesDate(String minutes) {
        int daysInt = Integer.parseInt(minutes);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.MINUTE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 解析excel中日期格式
     * @param strDate 字符串日期格式
     * @return
     */
    public static String excelDoubleToDate(String strDate) {
        if (strDate.length() == 5) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date tDate = doubleToDate(Double.parseDouble(strDate));
                return sdf.format(tDate);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 解析Excel日期格式
     * @param dVal
     * @return
     */
    private static Date doubleToDate(Double dVal) {
        Date tDate = new Date();
        // 系统时区偏移 1900/1/1 到 1970/1/1 的 25569 天
        long localOffset = tDate.getTimezoneOffset() * 60000;
        tDate.setTime((long) ((dVal - 25569) * 24 * 3600 * 1000 + localOffset));
        return tDate;
    }

    /**
     * 毫秒转时间戳
     * @param milliSecond milliSecond
     * @return String
     */
    public static String getDateByLong(String milliSecond) {
        Date date = new Date();
        date.setTime(Long.parseLong(milliSecond));
        return sdfTime.format(date);
    }

    /**
     * 推延月分（yyyy-MM）
     * @param month 月份 yyyy-MM
     * @param num   推延参数
     * @return 月份
     * @throws ParseException ParseException
     */
    public static String getNextMonth(String month, int num) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date monthDate = null;
        try {
            monthDate = format.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthDate);
        calendar.add(Calendar.MONTH, num);
        Date nextMothDate = calendar.getTime();
        return format.format(nextMothDate);
    }


    /**
     * 推延天数（yyyy-MM）
     * @param day 日期 yyyy-MM-dd
     * @param num 推延参数
     * @return 月份
     * @throws ParseException ParseException
     */
    public static String getNextDay(String day, int num) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dayDate = null;
        try {
            dayDate = format.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayDate);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        Date nextMothDate = calendar.getTime();
        return format.format(nextMothDate);
    }

    /**
     * 获取当前日期上一月
     * @param date 日期
     * @return date
     */
    public static String getBeforeMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DATE, 1);
        Date time = calendar.getTime();
        return sdfDay.format(time);
    }

    /**
     * 时间相减得月份
     * @param date1 date1
     * @param date2 date2
     * @return int
     * @throws ParseException ParseException
     */
    public static int getMonthSpace(String date1, String date2) {
        if (date1.equals(date2)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int result = 0;
        Calendar cal1 = new GregorianCalendar();
        try {
            cal1.setTime(sdf.parse(date1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal2 = new GregorianCalendar();
        try {
            cal2.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result =
                (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
        return result == 0 ? 1 : result;
    }


    /**
     * 获取日期区间内所有的月份(包含开始月份和结束月份)
     * @param startTime startTime
     * @param endTime   endTime
     * @return List<String>
     */
    public static List<String> getMonthBetween(String startTime, String endTime) {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(startTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        try {
            max.setTime(sdf.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 字符串的日期格式的计算
     * @param smdate 小
     * @param bdate  大
     * @return int
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(smdate));

            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取日期前后num天的日期
     * @param date date
     * @param num  num
     * @return String
     */
    public static String getAfterDay(String date, int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_YEAR, num);
        Date time = calendar.getTime();
        return sdfDay.format(time);
    }

    /**
     * 获取当前日期前num天的日期
     * @param date
     * @return
     */
    public static String getBeforeDay(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -num);
        Date time = calendar.getTime();
        return sdfDay.format(time);
    }

    public static long getLong(String frequencyType, int frequencyValue) {
        long period = 0;
        switch (frequencyType) {
            case "分":
                period = 60000;
                break;
            case "小时":
                period = 3600000;
                break;
            case "日":
                period = 3600000 * 24;
                break;
            case "周":
                period = 3600000 * 24 * 7;
                break;
            case "月":
                period = 3600000 * 24 * 30;
                break;
            case "年":
                period = 3600000 * 24 * 365;
                break;
        }
        return period * frequencyValue;
    }

    /**
     * 获取当前系统月份的上一个月份，
     * 返回字符串“yyyy-MM”
     */
    public static String getLastMonth() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        // 设置为上一个月
        date = calendar.getTime();
        return sdfMonthTwo.format(date);
    }

    public static String getHours(String beginTime, String endTime) {
        DateFormat df_parseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datass = null;
        Date datasss = null;
        try {
            datass = (Date) df_parseDate.parse(beginTime);
            datasss = (Date) df_parseDate.parse(endTime);
        } catch (Exception e) {
            return "";
        }
        double minutes = (datasss.getTime() - datass.getTime()) / (1000 * 60 * 60);
        return String.valueOf(minutes);
    }

    /**
     * 返回时间区间内的月份，格式yyyy-mm
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<String> getMonths(String beginTime, String endTime) {
        List<String> monthList = new ArrayList<>();
        do {
            monthList.add(beginTime);
            beginTime = getNextMonth(beginTime, 1);
        } while (!compareDateMore(beginTime + "-01", endTime + "-01"));
        return monthList;
    }

    /**
     * 取得当前月第一天
     * @param date date
     * @return String
     */
    public static String getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return sdfDay.format(c.getTime());
    }

    /**
     * 根据月份取得月份最后一天
     *
     * @param month month
     * @return String
     */
    public static String getLastDateOfMonth(String month) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdfMonthTwo.parse(month));
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = c.getTime();
        return sdfDay.format(time);
    }

    /**
     * 取得日期是周几
     *
     * @param date date
     * @return int
     */
    public static int getWeekDay(String date) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdfDay.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int week = c.get(Calendar.DAY_OF_WEEK);
        return week - 1;
    }
}
