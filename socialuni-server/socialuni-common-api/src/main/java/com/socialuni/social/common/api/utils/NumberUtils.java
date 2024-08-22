package com.socialuni.social.common.api.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author qinkaiyuan
 * @date 2019-03-01 21:59
 */
public class NumberUtils {
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

    public static Boolean strAllNotNumber(String val) {
        //遍历string数组
        for (char cha : val.toCharArray()) {
            //有一个不为数字
            if (Character.isDigit(cha)) {
                return false;
            }
        }
        return true;
    }

    public static Boolean strHasNoNumber(String val) {
        return !strIsAllNumber(val);
    }


    //## 标题二分法查找,返回插入点索引
    public static int getRank(List<Integer> list, Integer n) {
        list.sort(Collections.reverseOrder());

        // 将 List 转换回数组
        Integer[] arr = list.toArray(new Integer[0]);

        return binary(arr, n) + 1;
    }

    //## 标题二分法查找,返回插入点索引
    public static int binary(Integer[] arr, Integer n) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid].equals(n)) {
                return mid;
            } else if (arr[mid] > n) {
                high = mid - 1;
            } else if (arr[mid] < n) {
                low = mid + 1;
            }
        }
        return mid + 1;
    }
}
