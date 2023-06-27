package com.hzapt.common.utils;

import cn.hutool.core.util.IdUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * id生成工具类，用于生成id
 */
public class IdUtils {

    private static int count = 0;
    /**
     * 1.编号的格式为年月日八位数时间戳加上五位数顺序号。这里使用  SimpleDateFormat类来格式化当前日期。
     * 2.需要让顺序号自增，为了避免多线程并发访问时的问题，我们使用了synchronized关键字来加锁。
     * 3.第一次生成编号时，顺序号从0开始递增，递增的过程中要使用String.format方法来保证生成的顺序号始终为五位数。
     */
    public static synchronized String generateNumber() {
        String strDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String orderNumber = strDate + String.format("%05d", count);
        count++;
        return orderNumber;
    }

    public static String incrementString(String str) {
        Pattern pattern = Pattern.compile("(\\d+)$");  // 匹配最后面的数字部分
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int num = Integer.parseInt(matcher.group()) + 1;  // 将匹配到的数字字符串转为 int 类型并加上 1
            String newStr = String.valueOf(num);  // 将结果转为字符串类型
            // 补零操作，如果新的数字字符串长度比原来的短，则在前面补 0
            while (newStr.length() < matcher.group(1).length()) {
                newStr = "0" + newStr;
            }
            str = matcher.replaceFirst(newStr);  // 将原来的数字部分替换成新的数字字符串
        }
        return str;
    }

    /**
     * 使用IdUtil工具包生成id
     * @return
     */
    public static String id() {
        return IdUtil.objectId();
    }
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return IdUtil.randomUUID();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return IdUtils.simpleUUID();
    }





}

