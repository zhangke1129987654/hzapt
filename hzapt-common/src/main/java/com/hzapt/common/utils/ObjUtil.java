package com.hzapt.common.utils;

public class ObjUtil {
    public static boolean isEmpty(Object obj) {
        String str = String.valueOf(obj).replaceAll("[\\[\\]]","").replaceAll(",","");
        return "".equals(str.trim());
    }
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
        }
}
