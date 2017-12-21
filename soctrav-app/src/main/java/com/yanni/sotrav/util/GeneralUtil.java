package com.yanni.sotrav.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
public class GeneralUtil {
    public static final int ADDRESS_FIELD = 1;
    public static final int CITY_FIELD = 2;
    public static final int COUNTRY_FIELD = 3;
    private static final String CC_TYPE_NAME = "American Express";
    public static final long HOUR_MILLIS = 60 * 60 * 1000;
    public static final long DAY_MILLIS = 24 * HOUR_MILLIS;
    public static final int HDAP_ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;
    private static final String[] ALLOWED_DATE_FORMATS = new String[] { "yyyy-MM-dd'T'HH:mm:ss.SSS-ZZZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "MM/dd/yyyy" };
    private static final String[] ALLOWED_DATE_FORMATS_NO_TIMEZONE = new String[] { "yyyy-MM-dd'T'HH:mm:ss.SSS", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
    public static final char[] NONCONFUSING_ALPHABET = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z', };
    public static final Random rand = new Random();
    public static final List<Day> holidays = new ArrayList<Day>();
    public static final String PACIFIC_TIME = "America/Los_Angeles";

    public static final String COUNTRY_CODE_US = "US";
    public static final String COUNTRY_NAME_US = "United States of America";
    public static final String COUNTRY_CODE_CANADA = "CA";
    public static final String COUNTRY_NAME_CANADA = "Canada";

    public static boolean isUsOrCanadian(String code, String name) {
        return COUNTRY_CODE_US.equalsIgnoreCase(code) || COUNTRY_CODE_CANADA.equalsIgnoreCase(code)
            || COUNTRY_NAME_US.equalsIgnoreCase(name) || COUNTRY_NAME_CANADA.equalsIgnoreCase(name);
    }

    public static boolean isUsCountryCodeOrName(String code, String name) {
        return COUNTRY_CODE_US.equalsIgnoreCase(code) || COUNTRY_NAME_US.equalsIgnoreCase(name);
    }

    static {
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2011-12-26"))); //Christmas 2011
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2012-01-02"))); //New Years Day 2012
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2012-05-28"))); //Memorial Day 2012
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2012-07-04"))); //Independence Day 2012
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2012-09-03"))); //Labor Day 2012
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2012-11-22"))); //Thanksgiving 2012
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2012-11-23"))); //Day After Thanksgiving 2012
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2012-12-25"))); //Christmas 2012
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2013-12-24"))); //Christmas Eve 2013
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2013-12-25"))); //Christmas Day 2013
        holidays.add(new Day(GeneralUtil.convertStringtoDate("2014-01-01"))); //New Years Day 2014
    }

    private static final String TOLL_FREE_AREA_CODE = "1(800|888|877|866|855|844|833|822|88(\\d))"; //833,822,880 through 887,and 889 are not in use, but reserved for toll free
    private static final String TOLL_FREE_NUMBER = TOLL_FREE_AREA_CODE + "(\\d){7}";
    private static final String SERVICE_NUMBER = "5551212$";
    private static Pattern TOLL_FREE_AREA_CODE_PATTEN = Pattern.compile(TOLL_FREE_AREA_CODE);
    private static Pattern TOLL_FREE_NUMBER_PATTEN = Pattern.compile(TOLL_FREE_NUMBER);
    private static Pattern SERVICE_NUMBER_PATTEN = Pattern.compile(SERVICE_NUMBER);

    public static boolean isTollFreeNumber(String dnis) {
        if (dnis != null) {
            int index = dnis.lastIndexOf('*');
            if (index > 0) { //ignore * at the beginning
                dnis = dnis.substring(0, index); //remove custom tag if any
            }
            if (dnis.length() > 6) {
                return isAreaCodeTollFree(dnis);
            }
        }
        return false;
    }

    public static boolean isAreaCodeTollFree(String dnis) {
        if(dnis == null || dnis.length() < 4){
            return false;
        }

        if ('*' == dnis.charAt(0)) { //if it's a * code call, check toll free number
            return TOLL_FREE_NUMBER_PATTEN.matcher(dnis).find()
                    && false == SERVICE_NUMBER_PATTEN.matcher(dnis).find();
        } else {
            return TOLL_FREE_AREA_CODE_PATTEN.matcher(dnis).region(0, 4).matches() //if it's not a * code call, check exact match
                    && false == SERVICE_NUMBER_PATTEN.matcher(dnis).region(4, dnis.length()).matches();
        }
    }

    public static String getString(Map<String, Object> map, String key) {
        Object valueObj = getValue(map, key);
        if (null != valueObj) {
            if (valueObj instanceof String) {
                return (String) valueObj;
            }
        }
        return null;
    }

    public static Boolean getBoolean(Map<String, Object> map, String key) {
        Object valueObj = getValue(map, key);
        if (null != valueObj && valueObj instanceof Boolean) {
            return (Boolean) valueObj;
        }
        return Boolean.valueOf(getString(map, key));
    }

    public static Object getValue(Map<String, Object> map, String key) {
        if (null != map && false == isBlank(key)) {
            return map.get(key);
        }
        return null;
    }

    public static Integer getInteger(Map<String, Object> map, String key) {
        Object valueObj = getValue(map, key);
        if (null != valueObj) {
            if (valueObj instanceof Integer) {
                return (Integer) valueObj;
            }
            if (valueObj instanceof String) {
                try {
                    return Integer.parseInt((String) valueObj);
                } catch (NumberFormatException e) {
                    //squash
                }
            }
        }
        return null;
    }


    public static int parseInt(String intStr, String errorMessage) {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static int getDaysBetween(Date startDate, Date endDate) {
        if (null == startDate || null == endDate) {
            return 0;
        }
        Day start = new Day(startDate);
        Day end = new Day(endDate);
        return start.daysBetween(end);
    }

    public static final boolean isBlank(String str) {
        if(null == str) {
            return true;
        }
        if(str.length() < 1) {
            return true;
        }
        return false;
    }

    public static final boolean isPositive(Integer num) {
        if(null == num) {
            return false;
        }
        return num > 0;
    }

    public static final boolean isZero(Integer num) {
        if (null == num) {
            return true;
        }
        return num == 0;
    }

    public static final boolean isPositive(BigDecimal num) {
        if (null == num) {
            return false;
        }
        return num.compareTo(BigDecimal.ZERO) > 0;
    }

    public static final boolean isZero(BigDecimal num) {
        if(null == num) {
            return true;
        }
        return num.compareTo(BigDecimal.ZERO) == 0;
    }

    public static final int toPrimitive(Integer i) {
        if (null == i) {
            return 0;
        }
        return i.intValue();
    }

    public static final BigDecimal convertToMoney(BigDecimal x) {
        return x.setScale(2, HDAP_ROUNDING_MODE);
    }

    public static final BigDecimal convertToMoney(double x) {
        return convertToMoney(BigDecimal.valueOf(x));
    }

    public static final BigDecimal moneyMultiply(BigDecimal amount, double multiplicand) {
        return convertToMoney(amount.multiply(BigDecimal.valueOf(multiplicand)));
    }

    public static final BigDecimal moneyMultiply(double amount, double multiplicand) {
        return convertToMoney(BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(multiplicand)));
    }

    public static final BigDecimal moneyMultiply(BigDecimal amount, BigDecimal multiplicand) {
        return convertToMoney(amount.multiply(multiplicand));
    }

    public static final BigDecimal moneyMultiply(BigDecimal amount, int quantity) {
        return moneyMultiply(amount, BigDecimal.valueOf(quantity));
    }

    public static final List<String> convertDelimitedStringToListOfStrings(String s, String delimiter) {
        List<String> list = new ArrayList<String>();
        if(null != s && s.length() > 0) {
            String[] splitArray = s.split(delimiter);
            for(String element : splitArray) {
                if(null != element && element.length() > 0) {
                    list.add(element);
                }
            }
        }
        return list;
    }

    public static final List<String> convertCommaSeperatedStringToListOfStrings(String s) {
        return convertDelimitedStringToListOfStrings(s, ",");
    }

    public static final List<Integer> convertCommaSeperatedStringToListOfIntegers(String s) {
        List<Integer> list = new ArrayList<Integer>();
        if(null != s && s.length() > 0) {
            String[] splitArray = s.split(",");
            for(String element : splitArray) {
                if(null != element && element.length() > 0) {
                    try {
                        list.add(Integer.parseInt(element));
                    } catch(NumberFormatException e) {
                        //LOG.warn("Found a non-interger value while trying to convert a string of comma seperated values to a list of Integers. String: "+s);
                    }
                }
            }
        }
        return list;
    }

    public static final String convertListOfStringsToDelimitedString(Collection<String> list, String delimiter) {
        if(null == list || list.size() < 1) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(String cur : list) {
            if(null != cur && cur.length() > 0) {
                s.append(cur).append(delimiter);
            }
        }
        if(s.length() > delimiter.length()) {
            s.setLength(s.length() - delimiter.length()); //remove trailing delimiter
        }
        return s.toString();
    }

    public static final String convertListOfIntegersToCommaSeperatedString(Collection<Integer> list) {
        if(null == list || list.size() < 1) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Integer cur : list) {
            if(null != cur) {
                s.append(cur).append(",");
            }
        }
        if(s.length() > 0) {
            s.setLength(s.length() - 1); //remove trailing comma
        }
        return s.toString();
    }

    public static final String convertListOfLongsToCommaSeperatedString(Collection<Long> list) {
        if(null == list || list.size() < 1) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long cur : list) {
            if(null != cur) {
                s.append(cur).append(",");
            }
        }
        if(s.length() > 0) {
            s.setLength(s.length() - 1); //remove trailing comma
        }
        return s.toString();
    }

    public static Date getDaysFromNow(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date getMonthsFromNow(int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static Date getEarliestDate(Date d1, Date d2) {
        if(null == d1 && null == d2) {
            return null;
        }
        if(null == d1) {
            return d2;
        }
        if(null == d2) {
            return d1;
        }

        if(d1.before(d2)) {
            return d1;
        } else {
            return d2;
        }
    }

    public static final Date getMaxDate(List<Date> dates, boolean nullIsInfinite) {
        Date max = null;
        if(null == dates || dates.size() < 1) {
            return null;
        }

        for(Date curDate : dates) {
            if(null == curDate) {
                if(nullIsInfinite) {
                    return null;
                }else {
                    continue;
                }
            }
            if(null == max) {
                max = curDate;
                continue;
            }
            if(max.before(curDate)) {
                max = curDate;
            }
        }
        return max;
    }



    public static String generatePassword() {
        char[] pwd = new char[8];
        char[] specialChars = { '!', '@', '$', ';', '='}; //[\w!@$\^=_\-\.';\[\]\{\}\(\)]{6,12}
        char[] nums = { '0', '1', '2', '3', '4', '5', '6', '7', '8','9'};
        int j = 0;
        for (int i = 0; i < pwd.length; i++) {
            j = i % 4;
            switch (j) {
                case 1 :
                    pwd[i] = Character.toUpperCase(NONCONFUSING_ALPHABET[rand.nextInt(NONCONFUSING_ALPHABET.length)]);
                    break;
                case 2 :
                    pwd[i] = NONCONFUSING_ALPHABET[rand.nextInt(NONCONFUSING_ALPHABET.length)];
                    break;
                case 3 :
                    pwd[i] = nums[rand.nextInt(10)];
                    break;
                default :
                    pwd[i] = specialChars[rand.nextInt(4)];
                    break;
            }

        }
        return new String(pwd);
    }

    /**
     * Method used to add selected values to list from
     * comma separated string.
     *
     * @param strValue
     * @return aList
     */
    public static List<String> addToArrayList(String strValue)
    {
        List<String> aList = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(strValue, ",");
        while (st.hasMoreTokens()) {
            aList.add(st.nextToken());
        }
        return aList;
    }

    /**
     * Adds the element to the index requested, adding null to any entries before it if necessary
     * 
     * @param list
     * @param element
     * @param index
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addToListWithPadding(List list, Object element, int index)
    {
        if(list == null) list = new ArrayList();

        while(index > list.size())
        {
            list.add(null);
        }
        if(index < list.size() && null == list.get(index))
        {
            list.remove(index);
        }
        list.add(index, element);
    }

    /**
     * Method used to check the blank value existence
     * in the given string.
     *
     * @param sValue
     * @return String
     */
    public static String checkForNull(String sValue)
    {
        if(sValue == null || sValue.equals("") || sValue.equalsIgnoreCase("select") ) {
            sValue = "";
        }
        return sValue;
    }




    /**
     * Method used to convert given string into date
     * 
     * @param sDate
     * @return Date
     */
    public static Date convertStringtoDate(String sDate) {
        for (String format : ALLOWED_DATE_FORMATS) {
            Date d = convertStringtoDate(sDate, format);
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    public static Date convertStringtoDate(String sDate, List<String> allowedDateFormats) {
        for (String format : allowedDateFormats) {
            Date d = convertStringtoDate(sDate, format);
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    /**
     * Method used to convert given string into date
     * object.
     *
     * @param sDate
     * @param sFormat
     * @return Date
     */
    public static Date convertStringtoDate(String sDate, String sFormat) {
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(sFormat);
            date = df.parse(sDate);
        }
        catch(Exception ex) {
            // Squash
        }
        return date;
    }

    /**
     * Method used to convert given date into string.
     *
     * @param date
     * @param sFormat
     * @return String
     */
    public static String convertDatetoString(Date date, String sFormat) {
        DateFormat dateFormat = new SimpleDateFormat(sFormat);
        String s = null;
        try {
            s = dateFormat.format(date);
        } catch (Exception e) {
            //squash
        }
        return s;
    }

    public static String convertDatetoString(Date date) {
        for (String format : ALLOWED_DATE_FORMATS) {
            String s = convertDatetoString(date, format);
            if (s != null) {
                return s;
            }
        }
        return null;
    }
    public static String convertDatetoStringDropTimezone(Date date) {
        for (String format : ALLOWED_DATE_FORMATS_NO_TIMEZONE) {
            String s = convertDatetoString(date, format);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    public static Date addSecondsToDate(Date date, int seconds) {         
        return new Date(date.getTime() + seconds * 1000);
    }

    private  static void fillStackTraceAsCause(StringBuilder s, Throwable causedTrace) {
        s.append("Caused by: ").append(causedTrace);
        StackTraceElement[] trace = causedTrace.getStackTrace();
        for (StackTraceElement st : trace) {
            s.append("\n\tat ").append(st);
        }

        Throwable rootCause = causedTrace.getCause();
        if (rootCause != null) {
            fillStackTraceAsCause(s, rootCause);
        }
    }

    public static void fillStackTrace(Throwable t, StringBuilder sb) {
        sb.append(t.toString());
        StackTraceElement[] trace = t.getStackTrace();
        for (StackTraceElement st : trace) {
            sb.append("\n\tat ").append(st);
        }

        Throwable rootCause = t.getCause();
        if (rootCause != null) {
            fillStackTraceAsCause(sb, rootCause);
        }
    }

    public static String convertToString(Throwable t) {
        if (null == t) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        GeneralUtil.fillStackTrace(t, sb.append("<pre>"));
        sb.append("</pre><hr/></br>");
        return sb.toString();
    }

    /**
     * Create parent directory structure if one doesn't exists.
     * @param file
     */
    public static void buildFileSystemPath(File file) {
        if (file != null) {
            File parent = file.getParentFile();
            if (parent !=null && ! parent.exists() ) {
                parent.mkdirs();
            }
        }
    }

    public static Date getNextDay(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if (cal.get(Calendar.MONTH) == 11
                    && cal.getActualMaximum(Calendar.DATE) == cal.get(Calendar.DATE)) {
                cal.roll(Calendar.YEAR, 1);
                cal.set(Calendar.MONTH, Calendar.JANUARY);
                cal.set(Calendar.DATE, 1);
            } else {
                cal.roll(Calendar.DAY_OF_YEAR, 1);
            }
            return cal.getTime();
        }
        return null;
    }



    public static String getFormatedTransactionAmount(Double transAmount){
        NumberFormat formatter = new DecimalFormat(".00");
        String strTransAmt =  formatter.format(transAmount);
        return strTransAmt;
    }


    public static String maskCCNumber(String actualCCNumber, String ccTypeName) {
        //Need to mask the Credit Card Number before it is sent to the UI for display
        int ccNumLen = actualCCNumber.length();
        StringBuilder maskedCCNumber = new StringBuilder();
        if(ccNumLen != 0) {
            String lastFourDigitCCNum = actualCCNumber.substring((ccNumLen - 4), ccNumLen);
            if(ccTypeName != null && !CC_TYPE_NAME.equals(ccTypeName)) {
                for(int i=0; i<(ccNumLen - 4); i++){
                    if((i%4)==0){
                        maskedCCNumber.append(" ");
                    }
                    maskedCCNumber.append("*");
                }
                maskedCCNumber.append(" ").append(lastFourDigitCCNum);
            } else {
                int temp=4;
                for(int i=0; i<(ccNumLen - 4); i++) {
                    if((i%temp)==0){
                        maskedCCNumber.append(" ");
                    }
                    maskedCCNumber.append("*");
                    if(i==4) {
                        temp = 10;
                    }
                }
                maskedCCNumber.append(lastFourDigitCCNum);
            }
        }
        return maskedCCNumber.toString();
    }

    public static BigDecimal money(BigDecimal num) {
        if (num != null) {
            return num.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
        return null;
    }

    public static String moneyFormat(BigDecimal num) {
        num = money(num);
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        return n.format(num.doubleValue());
    }


    public static Date makeDateUTC(Date date, String timeZoneName)
    {
        return updateDateWithTimezone(date, timeZoneName, true);
    }

    public static Date makeDateLocal(Date date, String timeZoneName)
    {
        return updateDateWithTimezone(date, timeZoneName, false);
    }

    private static Date updateDateWithTimezone(Date date, String timeZoneName, boolean toUTC) {
        if (null != date && null != timeZoneName) {
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneName);
            long time = date.getTime();
            long timeZoneOffset = timeZone.getOffset(time);
            if (toUTC) {
                timeZoneOffset *= -1;
            }
            date = new Date(time + timeZoneOffset);
        }
        return date;
    }

    public static File convertUlawToMP3(File ulawFile,File mp3File){
        String  conversionCommand = null;
        conversionCommand = "/usr/local/bin/sox -t ul {0} -t mp3 -r 44100 {1}";
        if(null == conversionCommand){
            conversionCommand = "/usr/local/bin/sox -t ul {0} -t mp3 -r 44100 {1}";
        }
        String soxCommand = MessageFormat.format(conversionCommand,new Object[]{ulawFile.getAbsoluteFile(),mp3File.getAbsoluteFile()});
        try
        {
            Process process = Runtime.getRuntime().exec(soxCommand);
            process.waitFor();
            InputStream inP = process.getInputStream();
            if ( inP.available() == 0 ) {
                inP = process.getErrorStream();
            }
            BufferedInputStream buff = new BufferedInputStream(inP);
            byte b[] = new byte[1024];

            try {
                while(buff.read(b) != -1){
                    //LOG.debug("SOX Output:  " + new String(b));
                }
            }
            catch(Exception ex){
                //LOG.error("Exception reading SOX process output:", ex);
            }
            finally {
                buff.close();
                inP.close();
            }
        }
        catch(Exception e){
            //LOG.error("Exception launching SOX process:", e);
        }
        return mp3File;
    }

    public static String replaceVars(String str, Map<String, String> map) {
        if (null == str) {
            return null;
        }
        if (null == map || map.size() < 1) {
            return str;
        }
        for(Entry<String, String> rawKey : map.entrySet()) {
            if(null == rawKey  || null == rawKey.getKey() || null == rawKey.getValue()) {
                continue;
            }
            String key = "${"+rawKey.getKey()+"}";
            str = str.replace(key, rawKey.getValue());
        }
        return str;
    }

    public static String toInitCap(String s) {        
        if (null != s && s.length() > 0 && false == Character.isUpperCase(s.charAt(0))) {
            if (s.length() == 1) {
                s = s.toUpperCase();
            } else {
                s = s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        }
        return s;
    }

    public static String convertInputStreamToString(InputStream is) {
        Writer writer = new StringWriter();
        try {
            if (is != null) {			    
                char[] buffer = new char[1024];
                try {
                    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int n;
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);	
                    }
                } finally {
                    is.close();
                }
            }
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static int getNumberOfBusinessDays(Date startDate, Date endDate) {
        if (null == startDate || null == endDate) {
            return 0;
        }
        int numDays = 0;
        Day currentDay = new Day(startDate);
        Day end = new Day(endDate);

        while (currentDay.isBefore(end)) {
            if (isBusinessDay(currentDay)) {
                ++numDays;
            }
            currentDay = currentDay.addDays(1);
        }
        return numDays;
    }

    public static boolean isBusinessDay(Date date) {
        return isBusinessDay(new Day(date));
    }
//
    public static boolean isBusinessDay(Day day) {
        if (day.getDayOfWeek() == Calendar.SATURDAY || day.getDayOfWeek() == Calendar.SUNDAY) {
            return false;
        }
        if (isHoliday(day)) {
            return false;
        }
        return true;
    }

    public static boolean isHoliday(Day day) {
        for (Day holiday : holidays) {
            if (day.compareTo(holiday) == 0) {
                return true;
            }
        }
        return false;
    }

    public static Date getNextBusinessDay(Date date) {
        Date nextBusinessDate = date;
        do{
            if(isBusinessDay(nextBusinessDate)){
                break;
            }else{
                nextBusinessDate = getNextDay(nextBusinessDate); 
            }
        }while(true);        
        return nextBusinessDate;
    }

    public static Date getTodayMidnight() {
        Calendar now=Calendar.getInstance();		
        now=setMidnight(now);
        return now.getTime();
    }

    //TODO junit
    public static Date getEndOfDay(Date date) {
        Calendar now=setEndOfDay(date);	
        return now.getTime();
    }

    public static Date getMidnight(Date date) {
        Calendar cal=Calendar.getInstance();	
        cal.setTime(date);
        cal=setMidnight(cal);
        return cal.getTime();
    }

    public static Calendar setMidnight(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public static Calendar setEndOfDay(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal;
    }

    public static boolean areValuesSameWithNullMatchingBlank(Object object1, Object object2)
    {
        if (object1 == null) {
            object1 = "";
        }
        if (object2 == null) {
            object2 = "";
        }
        return object1.equals(object2);
    }

    public static boolean areValuesSame(Object object1, Object object2)
    {
        if(null == object1 && null == object2)
        {
            return true;
        }
        else
        {
            return null != object1 && null != object2 && object1.equals(object2);
        }
    }

//    public static <E> String join(Collection<E> s, String delimiter)
//    {
//        return StringUtils.join(s, delimiter);
//    }

    public static boolean listHasElements(List<?> list) {
        return list != null && list.isEmpty() == false;
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static String getFirstCustomTag(String customTag) {
        if(null != customTag && false == customTag.isEmpty()) {
            if(customTag.charAt(0) == ',' && customTag.length() > 1) {
                customTag = customTag.substring(1, customTag.length());
            }

            if(customTag.indexOf(',') > 0) {
                customTag = customTag.substring(0, customTag.indexOf(','));
            }
        }
        return customTag;
    }

    public static String convertDateToStringGMT(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String s = null;
        try {
            s = dateFormat.format(date);
        } catch (Exception e) {
            //squash
        }
        return s;
    }

    public static Date convertDateToTimeZone(Date date, TimeZone timeZone) {
        if (null != date && null != timeZone) {
            long convertedTime = date.getTime() + timeZone.getOffset(date.getTime());
            return new Date(convertedTime);
        }
        return date;
    }

//    public static Date toPST(Date date) {
//        return getGMTMinus8Calendar(date).getTime();
//    }
//
//    public static Calendar toGMTMinus8Calendar(Date date) {
//        return getGMTMinus8Calendar(date);
//    }

//    public static Calendar dateToGmtMinus8OffsettingForMidnight(Date date) {
//        DateTime currentDate = new DateTime(date);
//        if(timeIsAtEndOfDay(currentDate)) {
//            return convertDateTimeToGMTMinus8Calendar(currentDate);
//        }
//        currentDate = currentDate.plusSeconds(1);
//        return convertDateTimeToGMTMinus8Calendar(currentDate);
//    }
//
//    private static Calendar convertDateTimeToGMTMinus8Calendar(DateTime currentDate) {
//        return currentDate.withZoneRetainFields(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-8"))).toCalendar(Locale.US);
//    }
//
//    private static boolean timeIsAtEndOfDay(DateTime currentDate) {
//        return currentDate.getHourOfDay() == 23 && currentDate.getMinuteOfHour() == 59 && currentDate.getSecondOfMinute() == 59;
//    }
//
//    public static Calendar getGMTMinus8Calendar(Date date) {
//        return convertDateTimeToGMTMinus8Calendar(new DateTime(date));
//    }

    public static void randomShuffle(List<? extends Object> list) {
    	int total = list.size();
    	Random rand = new Random();
    	for (int i = total-1; i > 0; i--) {
    		int j = rand.nextInt(i);
    		swap(list, i, j);
    	}
    }
    
    public static void swap(List<? extends Object> list, int index1, int index2) {
    	swapHelper(list, index1, index2);   	
    }
    
    private static <T> void swapHelper (List<T> list, int i, int j) {
    	list.set(i, list.set(j, list.get(i)));
    }


    public static boolean isBillingAlreadyStarted(Date billStartDate) {
        if(billStartDate != null) {
            Date today = new Date();
            Calendar todayCalendar = Calendar.getInstance();
            Calendar billStartDateCalendar = Calendar.getInstance();
            billStartDateCalendar.setTime(billStartDate);
            if(billStartDate.before(today) || areTheseDatesSame(todayCalendar,billStartDateCalendar)) {
                //LOG.debug(" is billing already started TRUE");
                return true;
            }
        }
        return false;
    }


    public static boolean areTheseDatesSame(Calendar date1, Calendar date2) {
        if(date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
                date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH)) {
            //LOG.debug("are these dates same TRUE");
            return true;
        }
        return false;
    }

    public static boolean isBillingStartingToday(Date billStartDate) {
        if(billStartDate != null) {
            Calendar today = Calendar.getInstance();
            Calendar billStartDateCalendar = Calendar.getInstance();
            billStartDateCalendar.setTime(billStartDate);
            if(areTheseDatesSame(billStartDateCalendar,today)) {
                //LOG.debug("isBilling starting today TRUE");
                return true;
            }
        }
        return false;
    }

    public static String truncateString(String str, int length) {
        if (str == null || length >= str.length()) {
            return str;
        }
        return length <= 0 ? "" : str.substring(0, length);
    }

    /**
     * this is a utility method to remove duplicates from a List of Strings
     * and keep the original order, return a new List
     */
    public static List<String> removeDuplicates(List<String> list) {
        return new ArrayList<String>(new LinkedHashSet<String>(list));
    }

    /**
     * this is a utility method to remove duplicates from a List of String and keep the original order
     * this method will keep the original list reference.
     * this method cannot pass in a List that generated by Arrays.asList(), will through UnsupportedOperationException
     * if you have a list that is created by Arrays.asList(), use the method above, removeDuplicates(),
     * to return a new List instead and assign it back to the original list variable
     */

    public static void removeDuplicatesFromList(List<String> list) {
        if (list != null && !list.isEmpty()) {
            Set<String> noDups = new LinkedHashSet<String>(list);
            list.clear();
            list.addAll(noDups);
        }
    }


    // keep the original method here as an example for bad code without ANY testing, it will throw IndexOutOfBoundException
    //if there is a duplicate in the list
    // since it has a newDids.clear(), we can be sure newDids is NOT generated by Arrays.asList()
    public static void originalRemoveDuplicatesInManagementKitHelperDO_NOT_USE(List<String> newDids) {
        Map<String, Integer> tempDids = new HashMap<String, Integer>();
        for (int i = 0; i < newDids.size(); i++) {
            if (newDids.get(i) != null && false == tempDids.containsKey(newDids.get(i))) {
                tempDids.put(newDids.get(i), i);
            }
        }
        newDids.clear();
        for (int i = 0; i < tempDids.size(); i++) {
            newDids.add("");
        }

        Iterator<String> itr = tempDids.keySet().iterator();
        while (itr.hasNext()) {
            String temp = itr.next();
            newDids.set(tempDids.get(temp), temp);
        }
    }


    public static List<String> truncateList(List<String> list, int size) {
        if (list == null || list.size() <= size) {
            return list;
        }
        return list.subList(0, size);
    }
	
    public static String toCamelCase(String s) {
        if (null != s && s.length() > 0 && Character.isUpperCase(s.charAt(0))) {
            if (s.length() == 1) {
                s = s.toLowerCase();
            } else if (false == Character.isUpperCase(s.charAt(1))) {
                s = s.substring(0, 1).toLowerCase() + s.substring(1);
            }
        }
        return s;
    }

}
