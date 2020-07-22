package com.hliedu.tools;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	/**
	 * Format:yyyy-MM-dd
	 */
	public static final String DATESHOWFORMAT = "yyyy-MM-dd";
	/**
	 * Format:yyyy年MM月dd日
	 */
	public static final String DATESHOWFORMAT2 = "yyyy年MM月dd日";
	/**
	 * Format:MM月dd日
	 */
	public static final String DATESTOREFORMAT2 = "MM月dd日";
	
	
	/**
	 * Format:yyyyMMdd
	 */
	public static final String DATESTOREFORMAT = "yyyyMMdd";
	

	/**
	 * Format:MMdd
	 */
	public static final String DATEMDSTOREFORMAT = "MMdd";


	/**
	 * FormatHHmmss
	 */
	public static final String TIMESTOREFORMAT = "HHmmss";


	/**
	 * Format:yyyyMMddHHmmss
	 */
	public static final String DATETIMESTOREFORMAT = "yyyyMMddHHmmss";


	/** */
	public static final String TIMESHOWFORMAT = "HH:mm:ss";
	
	public static final String TIMESHOWFORMAT2 = "HH时mm分";
	/**
	 * Format:yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATETIMESHOWFORMAT = "yyyy-MM-dd HH:mm:ss";
	

	/**
	 * Format:yyyy年MM月dd日 HH:mm:ss
	 */
	public static final String DATETIMESHOWFORMAT3 = "yyyy年MM月dd日 HH:00";
	
	
	public static final Long MINUTES_TEN = 600000L;
	
	/**
	 * 时间格式（yyyy-MM-dd 00:00:00），如：2015-07-16 ：00:00:00
	 */
	public static final String DATETIMESHOWFORMAT2 = "yyyy-MM-dd 00:00:00";

	/**
	 * 计算两个日期的间隔天数
	 * 
	 * @param startDate
	 *            开始时间，如：2008-12-03 11:00:00
	 * @param endDate
	 *            结束时间，如：2009-12-31 11:00:00
	 * @return long 间隔天数(long) 
	 */
	public static long getBetweenDays(String startDate, String endDate) {
		if (endDate == null || startDate == null){
			return -1;
		}
		Date dateStart=isDate(startDate);
		if(null==dateStart){
			return -1;
		}
		Date dateEnd=isDate(endDate);
		if(null==dateEnd){
			return -1;
		}
		return getBetweenDays(dateStart, dateEnd);
	}
	/**
	 * 计算两个日期的间隔天数
	 * 
	 * @param startDate
	 *            开始时间，如：2008-12-03 11:00:00
	 * @param endDate
	 *            结束时间，如：2009-12-31 11:00:00
	 * @return long 间隔天数(long) 
	 */
	public static long getBetweenDays(Date startDate, Date endDate) {
		if (endDate == null || startDate == null){
			return -1;
		}
		Long days = endDate.getTime() - startDate.getTime();
		days = days/(1000*60*60*24);
		return days;
	}
	/**
	 * 获取与指定日期相差指定 天数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterDate(String baseDate, int dayCount,  String patternString) {
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));
		Calendar calendar = Calendar.getInstance();
		if(DATETIMESHOWFORMAT.equals(patternString)){
			int hour = Integer.parseInt(baseDate.substring(11, 13));
			int minute = Integer.parseInt(baseDate.substring(14, 16));
			int second = Integer.parseInt(baseDate.substring(17, 19));
			calendar.set(year, month - 1, date, hour, minute, second);
		}else{
			calendar.set(year, month - 1, date);
		}
		calendar.set(year, month - 1, date);
		calendar.add(Calendar.DATE, dayCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String dateString = formatter.format(_date);
		return dateString;
	}
	/**
	 * 获取与指定日期相差指定 天数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterDate(Date baseDate, int dayCount, String patternString) {
		String _baseDate=getDateString(baseDate, DATETIMESHOWFORMAT);
		return getAfterDate(_baseDate, dayCount, patternString);
	}
	/**
	 * 获取与指定日期相差指定 月数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterMonth(String baseDate, int monthCount, String patternString) {
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));
		Calendar calendar = Calendar.getInstance();
		if(DATETIMESHOWFORMAT.equals(patternString)){
			int hour = Integer.parseInt(baseDate.substring(11, 13));
			int minute = Integer.parseInt(baseDate.substring(14, 16));
			int second = Integer.parseInt(baseDate.substring(17, 19));
			calendar.set(year, month - 1, date, hour, minute, second);
		}else{
			calendar.set(year, month - 1, date);
		}
		calendar.add(Calendar.MONTH, monthCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String dateString = formatter.format(_date);
		return dateString;
	}
	/**
	 * 获取与指定日期相差指定 月数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterMonth(Date baseDate, int monthCount, String patternString) {
		String _baseDate=getDateString(baseDate, DATETIMESHOWFORMAT);
		return getAfterMonth(_baseDate, monthCount, patternString);
	}
	/**
	 * 获取与指定日期相差指定 月数 并减去天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param dateCount
	 *            加或减去的天数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getEndDate(String baseDate, int monthCount, int dateCount, String patternString) {
		int day = Integer.parseInt(baseDate.substring(8, 10));
		String endDate = getAfterMonth(baseDate, monthCount, patternString);
		int endDay = Integer.parseInt(endDate.substring(8, 10));
		// 说明日期没变
		if (endDay == day) {
			// 月数为正则为减一
			if (monthCount > 0) {
				endDate = getAfterDate(endDate, dateCount, patternString);
			} else {
				endDate = getAfterDate(endDate, dateCount, patternString);
			}
		} else { // 日期已变
			if (monthCount < 0) {
				endDate = getAfterDate(endDate, dateCount, patternString);
			}
		}
		return endDate;
	}
	/**
	 * 获取与指定日期相差指定 月数 并减去天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param dateCount
	 *            加或减去的天数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getEndDate(Date baseDate, int monthCount, int dateCount, String patternString) {
		String _baseDate=getDateString(baseDate, DATETIMESHOWFORMAT);
		return getEndDate(_baseDate, monthCount, dateCount, patternString);
	}
	/**
	 * 当前日期转换为指定月数后 的日期
	 * 
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 转换后的日期
	 */
	public static String getBeforeMonth(int monthCount, String patternString) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		return formatter.format(_date);
	}

	/*** 
     * 两个日期相差多少秒 
     *  
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getTimeDelta(Date date1,Date date2){  
        long timeDelta=(date1.getTime()-date2.getTime())/1000;//单位是秒  
        int secondsDelta=timeDelta>0?(int)timeDelta:(int)Math.abs(timeDelta);  
        return secondsDelta;  
    }  
      
    /*** 
     * 两个日期相差多少秒 
     * @param dateStr1  :yyyy-MM-dd HH:mm:ss 
     * @param dateStr2 :yyyy-MM-dd HH:mm:ss 
 
     */  
    public static int getTimeDelta(String dateStr1,String dateStr2){  
        Date date1=parseDateByPattern(dateStr1, DATETIMESHOWFORMAT);  
        Date date2=parseDateByPattern(dateStr2, DATETIMESHOWFORMAT);  
        return getTimeDelta(date1, date2);  
    }  
    
    public static Date parseDateByPattern(String dateStr,String dateFormat){  
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
        try {  
            return sdf.parse(dateStr);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
	/**
	 * 日期格式化(String转换为Date)
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param patten
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static Date getDateToString(String dateStr, String patten) {
		if(StringUtils.isBlank(dateStr)){
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化(String转换为String)
	 * 
	 * @param date
	 *            日期字符串
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getDateString(String date, String patternString) {
		if (date == null)
			return "";
		if (date.length() < 10)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString, Locale.ENGLISH);
		int len=patternString.length();
		if(len>date.length()){
			patternString=patternString.substring(0, date.length());
		}
		return formatter.format(getDateToString(date, patternString));
	}

	/**
	 * 日期格式化(Date转换为String)
	 * 
	 * @param _date
	 *            日期
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getDateString(Date _date, String patternString) {
		String dateString = "";
		if (_date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(patternString);
			dateString = formatter.format(_date);
		}
		return dateString;
	}

	/**
	 * 日期格式转换 DATE to DATE
	 * 
	 * @param _date
	 *            日期
	 * @param patten
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static Date dateToDate(Date _date, String patten) {
		String dateStr = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			if (_date != null) {
				dateStr = formatter.format(_date);
			}
			return formatter.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 获得格式化日期之后的 String数据
	 * 
	 * @param dateLong
	 * @param patten
	 * @return
	 */
	public static String getDateOfString(Long dateLong, String patten){
		if (dateLong != null) {
			return (new SimpleDateFormat(patten).format(new Date(dateLong.longValue()))).toString();
		}
		return "";
	}

	/**
	 * 文本时间转换为时间对象
	 * 
	 * @param baseDate
	 *            日期字符串
	 * @return
	 */
	public static java.sql.Date getSqlDate(String baseDate) {
		if (baseDate == null || baseDate.length() == 0)
			return null;
		Date date = getDateToString(baseDate, DATESHOWFORMAT);
		return new java.sql.Date(date.getTime());
	}

	/**
	 * java.util.Date对象转换为java.sql.Date对象
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @return Date java.sql.Date对象
	 */
	public static java.sql.Date UtilDateToSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获取到指定样式的年月日(年月日参数为int型)
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param patternString
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(int year, int month, int date, String patternString) {
		String dateString = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		Date showDate = calendar.getTime();
		dateString = formatter.format(showDate);
		return dateString;
	}

	/**
	 * 获取到指定样式的年月日(年月日参数为String型)
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param patternString
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(String year, String month, String date, String patternString) {
		String dateString = "";
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(date);
			dateString = getDateString(y, m, d, patternString);
		} catch (Exception e) {
		}
		return dateString;
	}

	/**
	 * 获取当前日期
	 * 
	 * @param patternString
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateStr(String patternString) {
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String date = formatter.format(new Date(System.currentTimeMillis()));
		return date;
	}

	/**
	 * 验证输入的文本信息日期是否合
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date isDate(String dateStr) {
		String date_format_1 = "yyyy/MM/dd";
		String date_format_2 = "yyyy-MM-dd";
		String date_format_3 = "yyyyMMdd";
		String date_format_4 = "yyyy.MM.dd";
		String[] date_format = { date_format_1, date_format_2, date_format_3, date_format_4 };
		for (int i = 0; i < date_format.length; i++) {
				Date tempDate = isDate(dateStr,date_format[i]);
				if(null!=tempDate){
					return tempDate;
				}
		}
		return null;
	}
	
	/**
	 * 验证输入的文本信息日期是否合
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date isDate(String dateStr,String patternString) {
		if(StringUtils.isBlank(patternString)){
			patternString= DATESHOWFORMAT;
		}
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat(patternString);
			formatDate.setLenient(false);
			ParsePosition pos = new ParsePosition(0);
			Date tempDate = formatDate.parse(dateStr, pos);
			tempDate.getTime();
			return tempDate;
		} catch (Exception e) {
		}
		return null;
	} 
	/**
	 * 把Date转换为Calendar对象
	 * 
	 * @param d
	 *            Date对象
	 * @return Calendar对象
	 */
	public static Calendar getCalendar(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal;
	}
	
	/**
	 * 获取当前时间n小时后
	 * @param d
	 * @param n
	 * @return
	 */
	public static Date getNHourAfter(Date d,int n) {
		Calendar c = getCalendar(d);
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + n);
		return c.getTime();
	}
	
	/**
	 * 获取当前时间n分钟之后
	 * @param d
	 * @param n
	 * @return
	 */
	public static Date getNMinuteAfter(Date d , int n) {
		Calendar c = getCalendar(d);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + n);
		return c.getTime();
	}

	/**
	 * 将时间对象转换成指定的格式有小时
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDateTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATETIMESHOWFORMAT);
		return bartDateFormat.format(date);
	}
	
	/**
	 * 将时间对象转换成指定的格式有
	 * 
	 * @param date
	 * @return
	 */
	public static String parsTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(TIMESHOWFORMAT);
		return bartDateFormat.format(date);
	}

	/**
	 * 将时间对象转换成指定的格式无小时
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATESHOWFORMAT);
		return bartDateFormat.format(date);
	}

	/**
	 * 获取当前月第一天
	 * 
	 * @return
	 */
	public static String firstDate() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		return getDateString(firstDate, DATESHOWFORMAT);
	}

	/**
	 * 获取当前月第一天
	 * 
	 * @return
	 */
	public static String lastDate() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		return getDateString(lastDate, DATESHOWFORMAT);
	}

	/**
	 * 获取当前数据里的时间参数
	 * 
	 * @return
	 */
	public static String getDateStr() {
		return "sysdate";
	}
	/**
	 * 获取上一个月的日期
	 * @param date
	 * @return
	 */
	public static Date getUpMouth(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, -1);
		return ca.getTime();
	}

	/**
	 * 获取下一个月的日期
	 * @param date
	 * @return
	 */
	public static Date getNextMouth(String date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date,DATESHOWFORMAT));
		ca.add(Calendar.MONTH, 1);
		return ca.getTime();
	}
	
	/**
	 * 获取下一个月的日期
	 * @param date
	 * @return
	 */
	public static Date getNextMouth(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, 1);
		return ca.getTime();
	}
	
	/**
	 * 获取24小时之后的时间
	 * @param date
	 * @return
	 */
	public static Date getNextDay(String date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date , DATESHOWFORMAT));
		ca.add(Calendar.DATE , 1);
		return ca.getTime();
	}
	
	/**
	 * 获取日期的年
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的月
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.MONTH)+1;
	}

	/**
	 * 获取日期的日
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DATE);
	}

	/**
	 * 获取日期事第几周
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取上一个月的日期
	 * @param date
	 * @return
	 */
	public static Date getUpMouth(String date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date,DATESHOWFORMAT));
		ca.add(Calendar.MONTH, -1);
		return ca.getTime();
	}
	
	/**
	 * 获取日期的年
	 * @param date
	 * @return
	 */
	public static int getYear(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date,DATESHOWFORMAT));
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的月
	 * @param date
	 * @return
	 */
	public static int getMonth(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date,DATESHOWFORMAT));
		return ca.get(Calendar.MONTH)+1;
	}

	/**
	 * 获取日期的日
	 * @param date
	 * @return
	 */
	public static int getDay(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date,DATESHOWFORMAT));
		return ca.get(Calendar.DATE);
	}

	/**
	 * 获取日期的第几周
	 * @param date
	 * @return
	 */
	public static int getWeek(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date,DATESHOWFORMAT));
		return ca.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * 获取日期的第几小时
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 获取日期的第几分钟
	 * @param date
	 * @return
	 */
	public static int getMin(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.MINUTE);
	}
	/**
	 * 只能用于检测日期
	 * 检测d1 是否大于等于d2
	 * @param d1
	 * @param d2
	 * @return true d1 是否大于等于d2
	 */
	public static boolean checkMax(Date d1,Date d2){
		boolean flag=false;
		if(null!=d1){
			if(null!=d2){
				String d1s=getDateString(d1, "yyyyMMdd");
				String d12s=getDateString(d2, "yyyyMMdd");
				if(Double.valueOf(d1s)>=Double.valueOf(d12s)){
					flag=true;
				}
			}else{
				flag=true;
			}
		}
		return flag;
	}
	/**
	 * 检测d1 是否大于等于d2
	 * @param d1
	 * @param d2
	 * @return true d1 是否大于等于d2
	 */
	public static boolean checkMaxPrecise(Date d1,Date d2,String format){
		boolean flag=false;
		if(null!=d1){
			if(null!=d2){
				String d1s=getDateString(d1, format);
				String d12s=getDateString(d2, format);
				if(Double.valueOf(d1s)>=Double.valueOf(d12s)){
					flag=true;
				}
			}else{
				flag=true;
			}
		}
		return flag;
	}
	
	public static boolean isWeekend(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    if(Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)
	            || Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
	        return true;
	    }
	    return false;
	}
	

	/**
	 * 给定时间往后延给定分钟
	 * @param date
	 * @param minute
	 * @return
	 * @author doumingjun
	 * @create date 2012-06-27
	 */
	public static Date addMinutes(Date date, int minute) {
		if (null == date) date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return (Date) calendar.getTime();
	}
	
	public static Date parseDate(String date, String format) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat(format);
		return parser.parse(date);
	}
	
	public static String parseDateToString(Date date, String format) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat(format);
		return parser.format(date);
	}
	
	public static boolean parseTime(Date d1,Date d2){
		boolean flag=false;
		if(null!=d1){
			if(null!=d2){
				String d1s=getDateString(d1, "HHmmss");
				String d12s=getDateString(d2, "HHmmss");
				if(Double.valueOf(d1s)>=Double.valueOf(d12s)){
					flag=true;
				}
			}else{
				flag=true;
			}
		}
		return flag;
	}
	/**
	 * 获取某一时间离当前时间的距离[返回x秒前、x分前、x小时前、x天前]
	 * @param gmtCreate
	 * @param date
	 * @return
	 */
	public static String getBetweenFormat(Date gmtCreate, Date date) {
        try {
            String html = "0秒";
            long days = DateUtils.getBetweenDays(DateUtils.parseDateToString(gmtCreate, DateUtils.DATESHOWFORMAT),
                    DateUtils.parseDateToString(date, DateUtils.DATESHOWFORMAT));
            long second = (new Date().getTime() - gmtCreate.getTime()) / 1000;
            if (second > 0) {
                long s = second;
                String format;
                Object[] array;
                //long days = (s / ((60 * 60) * 24));
                Integer hours = (int) (s / (60 * 60));
                Integer minutes = (int) (s / 60 - hours * 60);
                Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);
                if (days > 365) {
                	return DateUtils.parseDateToString(gmtCreate, DateUtils.DATESHOWFORMAT2);
                }else if (days > 30) {
                	return DateUtils.parseDateToString(gmtCreate, DateUtils.DATESTOREFORMAT2);
                }else if (days > 0) {
                    format = "%1$,d天前";
                    array = new Object[]{days};
                } else if (hours > 0) {
                    format = "%1$,d小时前";
                    array = new Object[]{hours};
                } else if (minutes > 1) {
                    format = "%1$,d分钟前";
                    array = new Object[]{minutes};
                } else {
                    format = "刚刚";
                    array = new Object[]{seconds};
                }
                html = String.format(format, array);
            }
            return html;
        }catch (Exception ex){
        	return DateUtils.getDateString(gmtCreate, DateUtils.DATETIMESHOWFORMAT);
        }
    }
	
	/**
	 * 时间戳有效是否在间隔时间内有效
	 * @param sourceTimestamp 传入的时间
	 * @param patten 传入时间的格式
	 * @param proid 秒
	 * @return
	 */
	public static boolean timestampNotValid(String sourceTimestamp,String patten,Long proid){
		Date inDate = DateUtils.getDateToString(sourceTimestamp, patten);
		long timeSubscript = timeSubscript(inDate, proid);
		//如果
		if(timeSubscript > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取传入时间和系统时间的时间差，秒
	 * @param inDate 传入时间
	 * @param proid 传入时间往后推多少秒
	 * @return 若未正数，则传入的时间+推迟时间 > 当前系统时间
	 */
	public static long timeSubscript(Date inDate,Long proid){
		long inTime = inDate.getTime()/1000 + proid;
		long currentTime = new Date().getTime()/1000;
		return inTime - currentTime;
	}
	
	/**
	 * 获取当前时间到，明日某个时间点的秒数，如果为空，则取明日凌晨
	 * @return
	 */
	public static int getCurrentTimeToZeroTime(Date afterTime){
		if(afterTime == null){
			afterTime = DateUtils.getNextDay(DateUtils.getDateStr(DateUtils.DATESHOWFORMAT));
		}
		return DateUtils.getTimeDelta(new Date() , afterTime);
	}
    
	public static void main(String[] args) throws Exception {
		
		System.out.println(DateUtils.getDateOfString(System.currentTimeMillis(), DateUtils.DATETIMESTOREFORMAT));
//		Date pprice =new Date();
//		System.out.println(pprice);
//		System.out.println(pprice.getTime());
//		Long a=pprice.getTime()+480000;
//		System.out.println(a);
//        System.out.println(new Date(a));

		//parseTime(new Date(),getDateToString("08:15:00", "HH:mm:ss"));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        
//        System.out.println(getAfterDate("2014-06-25 00:00:00",1, DATETIMESHOWFORMAT));

	}
}
