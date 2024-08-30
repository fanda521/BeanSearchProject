package com.jeffrey.searchbeandemo.common;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class SysUtil {
    public static boolean notNull(Object tar) {
        return !isNull(tar);
    }
    public static final Date formatDate2Date(Date date, String format) {
        return formatString2Date(formatDate2String(date, format), format);
    }


    public static final String formatDate2String(Date date, String format, Locale locale) {
        if (isNull(date))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String dateString = "";
        try {
            dateString = sdf.format(date);
        } catch (Exception e) {
            log.error("", e);
        }
        return dateString;
    }


    public static final String formatDate2String(Date date, String format) {
        if (isNull(date))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String dateString = "";
        try {
            dateString = sdf.format(date);
        } catch (Exception e) {
            log.error("", e);
        }
        return dateString;
    }

    public static final Date formatString2Date(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date datum = new Date();
        try {
            datum = sdf.parse(dateString);
            return datum;
        } catch (ParseException e) {
            log.error("", e);
            return null;
        }

    }


    public static <T, R> R invokeFuncNullSafe(Function<T, R> func, T from) {
        return isNull(from) ? null : func.apply(from);
    }

    public static <T, R> R invokeFuncNullSafe(Function<T, R> func, T from, R defaultVal) {
        return isNull(from) ? defaultVal : func.apply(from);
    }

    public static <T> T nullOrElse(T tar, Supplier<T> defaultVal) {
        return isNull(tar) ? defaultVal.get() : tar;
    }

    public static <T> T nullOrElse(T tar, T defaultVal) {
        return isNull(tar) ? defaultVal : tar;
    }

    public static final boolean isNull(Object str) {
        if (str instanceof String) {
            return (str == null || str.toString().trim().length() == 0 || "NULL".equalsIgnoreCase(str.toString().trim()));
        } else {
            return str == null;
        }
    }

    public static final boolean isNotNull(Object str) {
        return !isNull(str);
    }

    public static final String padNumericalValues(Number number, int length) {
        String no = number.toString();
        if (no.length() == length)
            return no;
        int padLength = length-no.length();
        StringBuffer buf=new StringBuffer();
        for (int i = 0; i < padLength; i++)
        {
            buf.append("0");
        }
        no=buf.toString()+no;
        return no;
    }

    public static String convertSQL(String sql, Object[] params)
    {
        StringBuffer buf = new StringBuffer();
        String[] str = sql.split("[?]");
        int strIndex=0;
        Object[] obj = params;
        if (obj.length == 0)
            buf.append(sql);
        else
        {
            for (int i = 0; i < obj.length; i++)
            {
                buf.append(str[i]);
                buf.append("'");
                buf.append(obj[i]);
                buf.append("'");
                strIndex++;
            }
        }
        if(strIndex<str.length&&obj.length>0)
        {
            for (int i = strIndex; i < str.length; i++)
            {
                buf.append(str[strIndex]);
            }
        }

        return buf.toString();
    }

    public static final String formatDate(Date d, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    public static String transferLocal(String local) throws Exception {
        if ("zh-hant".equalsIgnoreCase(local) || "zh-HK".equalsIgnoreCase(local) || "zh-TW".equalsIgnoreCase(local)) {
            local = "zh_TW";
        } else if ("zh-hans".equalsIgnoreCase(local) || "zh-CN".equalsIgnoreCase(local)) {
            local = "zh_CN";
        } else if ("en-us".equalsIgnoreCase(local) || "en_us".equalsIgnoreCase(local)) {
            local = "en";
        }
        if (SysUtil.isNotNull(local) && !local.equals("zh_TW") && !local.equals("zh_CN") && !local.equals("en")) {
            throw new Exception("not support language : " + local);
        }
        return local;
    }

    public static final Date parseDate(String inputDate, String dateformat) throws ParseException {
        DateFormat df = new SimpleDateFormat(dateformat);
        df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return df.parse(inputDate);
    }
}
