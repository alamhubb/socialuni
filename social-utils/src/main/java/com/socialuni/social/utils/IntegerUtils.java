package com.socialuni.social.utils;

import java.util.Random;

/**
 * @author qinkaiyuan
 * @date 2019-03-01 21:59
 */
public class IntegerUtils {
    public static final Random random = new Random();

    public static Integer isNullReturnZeroOrSelf(Integer val) {
        if (val == null) {
            return 0;
        } else {
            return val;
        }
    }

    public static Boolean strIsAllNumber(String val) {
        //遍历string数组
        for (char cha : val.toCharArray()) {
            //有一个不为数字
            if (!Character.isDigit(cha)) {
                return false;
            }
        }
        return true;
    }

    public static Boolean strHasNoNumber(String val) {
        return !strIsAllNumber(val);
    }
}
