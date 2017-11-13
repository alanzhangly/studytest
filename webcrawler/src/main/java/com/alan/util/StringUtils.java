package com.alan.util;

/**
 * Created by Ke Zhang on 2017/10/13.
 */
public class StringUtils {

    public static boolean isEmpty(String str){
        if(str == null || str.trim().length() == 0){
            return true;
        }
        return false;
    }
}
