package com.baiyi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: BaiYi
 * @Description: 日期转换工具类
 * @Date: 2022/4/10 11:29
 */
public class DateUtils {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 按照指定的格式进行将时间转换为str
     * @param date 待转换的时间
     * @param format 转换格式
     * @return str类型的时间
     */
    public static String Date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将str转换为date类型
     * @param dateStr str类型时间
     * @return date
     */
    public static Date Str2Date(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
