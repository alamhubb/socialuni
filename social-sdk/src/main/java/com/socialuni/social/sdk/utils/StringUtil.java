package com.socialuni.social.sdk.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * @author qinkaiyuan
 * @date 2019-02-24 22:12
 */
public class StringUtil {
    //所有为空
    public static boolean isAllEmpty(String... stringList)  {
        //有一个不为空，则就不是全部为空
        return Arrays.stream(stringList).noneMatch(StringUtils::isNotEmpty);
    }

    //不是所有为空
    public static boolean isNotAllEmpty(String... stringList)  {
        //有一个不为空，则就不是全部为空
        return isAllEmpty(stringList);
    }

    //有一个为空
    public static boolean hasEmpty(String... stringList)  {
        return Arrays.stream(stringList).anyMatch(StringUtils::isEmpty);
    }

    //所有都不为空
    public static boolean isNotEmpty(String... stringList) {
        //没有为空的就是都不为空
        return Arrays.stream(stringList).noneMatch(StringUtils::isEmpty);
    }
}
