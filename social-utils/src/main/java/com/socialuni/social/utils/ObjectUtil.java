package com.socialuni.social.utils;



import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;

/**
 * @author qinkaiyuan
 * @date 2019-02-24 22:12
 */
public class ObjectUtil {
    //所有为空
    public static boolean isAllEmpty(Object... stringList) {
        //有一个不为空，则就不是全部为空
        return Arrays.stream(stringList).allMatch(ObjectUtils::isEmpty);
    }

    //不是所有为空
    public static boolean isNotAllEmpty(Object... stringList) {
        //有一个不为空，则就不是全部为空
        return isAllEmpty(stringList);
    }

    //有一个空
    public static boolean hasEmpty(Object... stringList) {
        //有一个不为空，则就不是全部为空
        return Arrays.stream(stringList).anyMatch(ObjectUtils::isEmpty);
    }

    //所有都不为空
    public static boolean isNotEmpty(Object... stringList) {
        //没有为空的就是都不为空
        return Arrays.stream(stringList).noneMatch(ObjectUtils::isEmpty);
    }
}
