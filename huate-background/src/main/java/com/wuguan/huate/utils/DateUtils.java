package com.wuguan.huate.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * 
 * @author wanpeng http://www.hotcomm.net/
 * @date 2018年3月26日 下午3:57:02
 */
public class DateUtils {

	public static final byte YEAR = 0x01;
	public static final byte MONTH = 0x02;
	public static final byte DAY = 0x03;
	public static final byte HOUR = 0x04;
	public static final byte MINUTE = 0x05;
	public static final byte SECOND = 0x06;

	public static final byte ADD = 0X01;
	public static final byte DEL = 0X02;

	/**
	 * 单个日期加减- 年|月|日|时|分|秒
	 * 
	 * @param date
	 * @param timeUnit
	 * @param methodUnit
	 * @param amount
	 * @return
	 */
	public static Date converTime(Date date, byte timeUnit, byte methodUnit, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		amount = methodUnit == DateUtils.ADD ? amount : -amount;
		switch (timeUnit) {
		case 0x1:
			cal.add(Calendar.YEAR, amount);
			break;
		case 0x02:
			cal.add(Calendar.MONTH, amount);
			break;
		case 0x03:
			cal.add(Calendar.DAY_OF_MONTH, amount);
			break;
		case 0x04:
			cal.add(Calendar.HOUR, amount);
			break;
		case 0x05:
			cal.add(Calendar.MINUTE, amount);
			break;
		default:
			cal.add(Calendar.SECOND, amount);
			break;
		}
		date = cal.getTime();
		return date;
	}

	/**
	 * 获取当前时间的字符串
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return tempDate.format(new java.util.Date());
	}

	/**
	 * 获取当前时间的字符串
	 * 
	 * @return
	 */
	public static String getTime() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		return tempDate.format(new java.util.Date());
	}

	/**
	 * 获取指定时间的年、月、日
	 * 
	 * @param dateTime
	 * @return
	 */
	public static Map<String, Integer> getYmd(Date dateTime) {
		Calendar now = Calendar.getInstance();
		now.setTime(dateTime);
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		Map<String, Integer> map = new HashMap<>();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		return map;
	}

	/**
	 * 获取上个月的月份、天数及本月的天数
	 * 
	 * @return
	 */
	public static Map<String, Integer> getLastMonDay() {
		Map<String, Integer> map = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		cal.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = cal.get(Calendar.DATE);
		map.put("curMonDay", maxDate);
		int lastMon = cal.get(Calendar.MONTH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int lastMonDay = cal.get(Calendar.DAY_OF_MONTH);
		map.put("lastMon", lastMon);
		map.put("lastMonDay", lastMonDay);
		return map;
	}

	/**
	 * 描述:获取上个月的最后一天
	 * 
	 * @return
	 */
	public static String getLastMaxMonthDate() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dft.format(calendar.getTime());
	}

	/**
	 * 描述:获取本月第一天零点
	 * 
	 * @return
	 */
	public static String getMonthFristDate() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 0);
		// 将秒至0
		calendar.set(Calendar.SECOND, 0);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 0);
		// 获得当前月第一天
		Date startDate = calendar.getTime();
		return dft.format(startDate);
	}

	/**
	 * 描述:获取本月最后一天23:59:59
	 * 
	 * @return
	 */
	public static String getMonthLastDate() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DATE));
		// 将小时至0
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 59);
		// 将秒至0
		calendar.set(Calendar.SECOND, 59);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 59);
		// 获得当前月第一天
		Date endDate = calendar.getTime();
		return dft.format(endDate);
	}

	/**
	 * 
	 * @Title: getFirstDateForYear
	 * @Description: 获取本年第一天
	 * @return
	 */
	public static String getFirstDateForYear() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return dft.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title: getFirstDateForYear
	 * @Description: 获取本年第一天
	 * @return
	 */
	public static String getLastDateForYear() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 1);
		calendar.set(Calendar.DAY_OF_YEAR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return dft.format(calendar.getTime());
	}

	/**
	 * 描述:获取昨天日期
	 * 
	 * @return
	 */
	public static String getYesterDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}

	/**
	 * 描述:获取明天
	 * 
	 * @return
	 */
	public static String getTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}

	/**
	 * 获得当天零时零分零秒
	 * 
	 * @return
	 */
	public static Map<String, String> initDateByDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, String> map = new HashMap<>();
		// 获取当天凌晨0点0分0秒Date
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		Date beginOfDate = calendar1.getTime();

		// 获取当天23点59分59秒Date
		Calendar calendar2 = Calendar.getInstance();
		calendar1.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
				23, 59, 59);
		Date endOfDate = calendar1.getTime();

		map.put("beginOfDate", formatter.format(beginOfDate));
		map.put("endOfDate", formatter.format(endOfDate));
		return map;
	}

	/**
	 * 获取两个时间之间的小时差
	 * 
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static long getDatePoor(Date endDate, Date nowDate) {
		// Map<String , Object> map = new HashMap<>();
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		// long nm = 1000 * 60;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少小时
		long hour = diff % nd / nh;
		return hour;
	}

	/**
	 * 取到 n个小时 以前时间
	 * 
	 * @param hours
	 * @return
	 * @throws ParseException
	 */
	public static String getBeforeDate(int n) {
		Calendar expireDate = Calendar.getInstance();
		expireDate.set(Calendar.HOUR_OF_DAY, expireDate.get(Calendar.HOUR_OF_DAY) - n);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(expireDate.getTime());
	}

	/**
	 * 取到 n分钟 以前时间
	 * 
	 * @param hours
	 * @return
	 * @throws ParseException
	 */
	public static String getBeforeMinute(int n) {
		Calendar expireDate = Calendar.getInstance();
		expireDate.set(Calendar.MINUTE, expireDate.get(Calendar.MINUTE) - n);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(expireDate.getTime());
	}

	/**
	 * 获取当前时间之前一年的时间
	 * 
	 * @return
	 */
	public static String getBeforeYearDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去一年
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		return format.format(c.getTime());

	}

	/**
	 * 获取当前时间之前两年的时间
	 * 
	 * @return
	 */
	public static String getBeforeTowYearDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去两年
		c.setTime(new Date());
		c.add(Calendar.YEAR, -2);
		return format.format(c.getTime());
	}

	/**
	 * 获得本周一0点时间
	 * 
	 * @return
	 */
	public static String getTimesWeekMonday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (dayWeek == 1) {
			dayWeek = 8;
		}
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		Date mondayDate = cal.getTime();
		return format.format(mondayDate);
	}

	/**
	 * 获得本周日0点时间
	 * 
	 * @return
	 */
	public static String getTimesWeekSunday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (dayWeek == 1) {
			dayWeek = 8;
		}
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
		Date sundayDate = cal.getTime();
		return sdf.format(sundayDate);
	}

	/**
	 * 获得当前日期的前N天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getBeforeNdayTime(int n) {
		// 获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(today);
		theCa.add(theCa.DATE, -n);
		Date start = theCa.getTime();
		return sdf.format(start);
	}

	/**
	 * 获得当前日期的前N天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getBeforeNdayTime1(int n) {
		// 获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(today);
		theCa.add(theCa.DATE, -n);
		Date start = theCa.getTime();
		return sdf.format(start);
	}

	/**
	 * 获得当前日期的前N天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getBeforeNdayTime2(int n) {
		// 获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date today = new Date();
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(today);
		theCa.add(theCa.DATE, -n);
		Date start = theCa.getTime();
		return sdf.format(start);
	}

	/**
	 * 获得当前时间前一年的时间
	 * 
	 * @return
	 */
	public static String getLastYearTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -1);
		return format.format(c.getTime());
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 */
	public static int getDaysBetween(String smdate, Date bdate) {
		long between_days = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(smdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Integer.parseInt(String.valueOf(between_days));
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) {
		String res = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleDateFormat.parse(s);
			long ts = date.getTime();
			res = String.valueOf(ts);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * @Title: dateToWeek
	 * @Description: 根据时间获取获取是周几
	 * @param datetime
	 * @return
	 */
	public static String dateToWeek(String datetime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		Date date;
		try {
			date = sdf.parse(datetime);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[w];
	}

	/**
	 * 时间加上N个小时
	 */
	public static Date addHour(int hour, Date reduction) {
		LocalDateTime now = date2LocalDateTime(reduction);
		LocalDateTime ldt2 = now.plusHours(hour);
		return localDateTime2Date(ldt2);
	}

	/**
	 * 时间加上N个天数
	 */
	public static Date addDay(int day, Date reduction) {
		LocalDateTime now = date2LocalDateTime(reduction);
		LocalDateTime ldt2 = now.plusDays(day);
		return localDateTime2Date(ldt2);
	}

	/**
	 * LocalDateTime转Date
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
		Date date = Date.from(zonedDateTime.toInstant());
		return date;
	}

	/**
	 * Date转LocalDateTime
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime date2LocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;
	}

	/**
	 * 
	 * @Title: getYear
	 * @Description: 获取根据num获取过去的年
	 * @param num
	 * @return
	 */
	public static String getYearBefor(int num) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -num);
		return format.format(cal.getTime());
	}

	/**
	 * 
	 * @Title: getIntegralTime
	 * @Description: 获取整点时间
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String getIntegralTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("HH:00").format(parse);
	}

	/**
	 * 
	 * @Title: compare
	 * @Description: 比較兩個日期的大小
	 * @param time 給定時間
	 * @param date 當前時間
	 * @return
	 * @throws ParseException
	 */
	public static Boolean compare(String time, Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date da = sdf.parse(time);
		if (date.before(da)) {
			return true;
		}
		return false;
	}

	/**
	 * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2 被比较的时间 为空(null)则为当前时间
	 * @param stype 返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static Integer compareDate(String date1, Date date2, int stype) {
		int n = 0;
		String formatStyle = "yyyy-MM-dd HH:mm:ss";
		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(date2);
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return c1.get(Calendar.DATE) >= c2.get(Calendar.DATE) ? n : n + 1;
	}

	/**
	 * 
	 * @Title: getYearMonth
	 * @Description: 获取指定时间的年月
	 * @param time
	 * @return
	 */
	public static String getYearMonth(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String format = sdf.format(date);
		String replace = format.replace("-", ".");
		return replace;
	}

	public static String getYearMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String format = sdf.format(date);
		String replace = format.replace("-", ".");
		return replace;
	}

	// 将20101125102503转换成2010-11-25 10:25:03输出
	public static String convertTime(String time) {
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
			return time = formatter1.format(formatter2.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getNo
	 * @Description: 获取一个唯一数字序列
	 * @param time
	 * @return
	 */
	public static String getNo(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(time);
	}

	/**
	 * 
	 * @Title: getMonthDiff
	 * @Description: 两个时间相差月数
	 * @param time
	 * @param d2
	 * @return
	 */
	public static int getMonthDiff(String time, Date d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = null;
		try {
			d1 = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值 
		int yearInterval = year1 - year2;
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
		if (month1 < month2 || month1 == month2 && day1 < day2) {
			yearInterval--;
		}
		// 获取月数差值
		int monthInterval = (month1 + 12) - month2;
		if (day1 < day2) {
			monthInterval--;
		}
		monthInterval %= 12;
		int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
		return monthsDiff;
	}

	public static void main(String[] args) throws ParseException {
		int daysBetween = getDaysBetween(null, new Date());
		System.out.println(daysBetween);

	}

}
