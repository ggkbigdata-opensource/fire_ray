package org.fire.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	/*SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm"); 
    SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //等价于now.toLocaleString()
    SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");*/
     
	public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static Date parseDate(String format, String datestr)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(datestr);
	}

	public static Date parseDate(SimpleDateFormat formatter, String datestr)
			throws ParseException {
		return formatter.parse(datestr);
	}
	
	public static String getTime(String type, String time) {
		if (StringUtils.isEmpty(time)) {
			return "11-01 15:35";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(type);
		String newTime = formatter.format(new Date(Long.parseLong(time)));
		return newTime;
	}

	public static String getTime(String type, long time) {
		if (time == 0) {
			return "11-01 15:35";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(type);
		String newTime = formatter.format(new Date(time));
		return newTime;
	}
	
	public static String format(Date date, String pattern) {
		SimpleDateFormat fastDateFormat = new SimpleDateFormat(pattern);
		return fastDateFormat.format(date);
	}

	/**
	 * 数据库表company的starttime 和 endtime 默认值为 “0” 正常值为 8 位数字的字符串
	 * 
	 * @param type
	 * @param date
	 * @return
	 */
	public static String getTime(String type, Date date) {
		String newTime;
		if (date == null) {
			newTime = "0";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat(type);
			newTime = formatter.format(date);
		}
		return newTime;
	}

	/**
	 * 传入日期数，计算后面的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date addDay(Integer days) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * 传入日期数，计算后面的日期
	 * 
	 * @param week
	 * @return
	 */
	public static Date addWeek(Integer week) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.WEEK_OF_MONTH, week);
		return calendar.getTime();
	}

	/**
	 * 传入月份数和天数，计算后面日期
	 * 
	 * @param month
	 * @return
	 */
	public static Date addMonth(Integer month, Integer days) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * 计算增加一段日期后的日期
	 * 
	 * @param beforeTime
	 * @param years
	 * @param months
	 * @param days
	 * @return
	 */
	public static String addPeriod(String beforeTime, int years, int months,
			int days) {
		String afterTime = "";
		try {
			Date before = DateUtil.getDateTime("yyyyMMdd", beforeTime);
			Calendar c = Calendar.getInstance();
			c.setTime(before);
			c.add(Calendar.YEAR, years);
			c.add(Calendar.MONTH, months);
			c.add(Calendar.DATE, days);
			afterTime = DateUtil.getTime("yyyyMMdd", c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return afterTime;
	}

	/**
	 * 计算增加一段时间后的 开始时间、结束时间
	 * 
	 * @param startTime
	 * @param endTime
	 * @param years
	 * @param months
	 * @param days
	 * @return map(key = startTime, key = endTime)
	 */
	public static Map<String, String> getIncreasedPeriodDateMap(
			String startTime, String endTime, int years, int months, int days) {
		Map<String, String> timeMap = new HashMap<String, String>();
		Date currentDate = DateUtil.getDateTime("yyyyMMdd",
				DateUtil.getTime("yyyyMMdd", new Date()));
		Date endDate = DateUtil.getDateTime("yyyyMMdd", endTime);
		if (currentDate.getTime() > endDate.getTime()) {
			startTime = DateUtil.getTime("yyyyMMdd", currentDate);
			endTime = startTime;
		}
		// add license time
		endTime = DateUtil.addPeriod(endTime, years, months, days);

		timeMap.put("startTime", startTime);
		timeMap.put("endTime", endTime);
		return timeMap;
	}

	/**
	 * 传入日期计算相差时间 类型是20120101
	 * 
	 * @throws java.text.ParseException
	 */
	public static int getMonthNum(String time1, String time2)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return (cal2.get(1) - cal1.get(1)) * 12 + (cal2.get(2) - cal1.get(2));
	}

	/**
	 * 传入时间，计算2个时间相差天数
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int getIntervalDays(String time1, String time2)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		long intervalMilli = cal1.getTime().getTime()
				- cal2.getTime().getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	}

	/**
	 * 返回现在时间是今年的第几周
	 * 
	 */
	public static int getWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getDay() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.DAY_OF_YEAR);
	}

	public static int getNowDay() {
		return Integer.decode(getTime("yyyyMMdd", new Date()));
	}

	/**
	 * 得到当年凌晨毫秒数
	 * 
	 */
	public static long getNowDayTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static long getNowDayTime1() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 2);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 返回本周开始和结束的时间
	 * 
	 * @return
	 */
	public static Map<String, Integer> getWeekUpAndDown() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar c = Calendar.getInstance();
		int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
		c.add(Calendar.DAY_OF_MONTH, -weekday);
		map.put("up", Integer.decode(getTime("yyyyMMdd", c.getTime())));
		c.add(Calendar.DAY_OF_MONTH, 6);
		map.put("down", Integer.decode(getTime("yyyyMMdd", c.getTime())));
		return map;
	}

	/**
	 * 返回上周开始和结束的时间
	 * 
	 * @return
	 */
	public static Map<String, Integer> getLastWeekUpAndDown() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar c = Calendar.getInstance();
		int weekday = c.get(Calendar.DAY_OF_WEEK) + 5;
		c.add(Calendar.DAY_OF_MONTH, -weekday);
		map.put("up", Integer.decode(getTime("yyyyMMdd", c.getTime())));
		c.add(Calendar.DAY_OF_MONTH, 6);
		map.put("down", Integer.decode(getTime("yyyyMMdd", c.getTime())));
		return map;
	}

	/**
	 * 转换字符串时间为标准时间
	 * 
	 * @throws java.text.ParseException
	 */
	public static Date getDateTime(String formatString, String time) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date date1 = null;
		if (!time.equals("0")
				&& !org.springframework.util.StringUtils.isEmpty(time)) {
			try {
				date1 = format.parse(time);
			} catch (ParseException e) {
			}
		}
		return date1;
	}

	/**
	 * 返回时间差，如xx前（传入时间）
	 * 
	 * @param datetime
	 * @throws ParseException
	 */
	public static String getTimeBetweenNow(long datetime) {
		String date = "";
		long time = System.currentTimeMillis();
		long l = time - datetime;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		if (day > 365) {
			date = day / 365 + "年前";
		} else if (day > 30) {
			date = day / 30 + "月前";
		} else if (day > 0) {
			date = day + "天前";
		} else if (day == 0 && hour > 0) {
			date = hour + "小时前";
		} else if (min > 0 && hour == 0) {
			date = min + "分钟前";
		} else if (min == 0) {
			date = s + "秒前";
		}
		// System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		return date;
	}

	/**
	 * 计算是否为限期内用户，如果用户过期，返回true，否则返回false
	 * 
	 * @param endTime
	 * @return
	 */
	public static boolean isOverdue(String endTime) {
		if (StringUtils.isEmpty(endTime) || !StringUtils.isNumeric(endTime)) {
			return false;
		}
		// 当前时间
		int now = Integer.parseInt(getTime("yyyyMMdd", new Date()));
		// 小于0，说明已经过期，否则未过期
		return Integer.parseInt(endTime) - now < 0;
	}

	public static String addMonth(String endtime, int month) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer integer = Integer.valueOf(endtime);
		Integer now = Integer.valueOf(format.format(new Date()));
		if (integer > now) {
			try {
				Date date = format.parse(endtime);
				calendar.setTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return endtime;
			}
		}
		calendar.add(Calendar.MONTH, month);
		return format.format(calendar.getTime());
	}

	/**
	 * 对指定时间追加分钟
	 * @param date 时间
	 * @param minute 分钟(参数可以是正整数，负整数)
	 * @return
	 */
	public static Date addMinute(Date date, Integer minute){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.add(Calendar.MINUTE, minute);
		return calendar1.getTime();
	}
	
	public static void main(String args[]) throws ParseException {
		System.out.println(new Date(1353057498051l));
		String endTime = "20170130";
		System.out.println(addMonth(endTime, 1));

        //System.out.println(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", "2015-04-10 14:00:00"));
        System.out.println(DateUtil.getTime("yyyy-MM-dd HH:mm:ss", new Date()));
    }

}
