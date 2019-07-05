package com.weisen.www.code.yjf.shopmall.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 获取yyyy-MM-dd HH:mm:ss 格式的当前时间
     * @return
     */
    public static String getDateForNow(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    /**
     * 将时间转换成yyyy-MM-dd HH:mm:ss 格式的时间
     * @param date
     * @return
     */
    public static String changeDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

    /**
     * 转换当前时间到月
     * @param date
     * @return
     */
    public static String changeDateToMonth(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

    /**
     * 转换当前时间到日
     * @param date
     * @return
     */
    public static String changeDateToDay(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

    /**
     * 转换当前时间的小时
     * @param date
     * @return
     */
    public static String changeDateToHour(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        String newDate = simpleDateFormat.format(date);
        return newDate;
    }

}
