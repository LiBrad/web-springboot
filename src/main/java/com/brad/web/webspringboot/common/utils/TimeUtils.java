package com.brad.web.webspringboot.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-05
 *
 * 时间工具
 */
public class TimeUtils {

    /**
     * 获取当前系统时间，格式为yyyy-MM-dd HH:mm:ss
     * */
    public static String getNowSystemTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
