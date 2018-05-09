package com.zng.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by John.Zhang on 2018/5/2.
 */
public class DateUtil {

    private static String LONG_FORMAT="yyyy-MM-dd hh:mm:ss";
    private static String SHORT_FORMAT="yyyy-MM-dd";

    public static String formatDate(Date date){
        if(date == null){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.LONG_FORMAT);
        return format.format(date);
    }

    public static String formatShortDate(Date date){
        if(date == null){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.SHORT_FORMAT);
        return format.format(date);
    }

}
