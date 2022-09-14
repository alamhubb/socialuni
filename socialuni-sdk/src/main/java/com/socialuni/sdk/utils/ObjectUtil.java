package com.socialuni.sdk.utils;



import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
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

    //合并两个java对象
    public static <T> T mergeObjects(T first, T second){
        Class<?> clas = first.getClass();
        Field[] fields = clas.getDeclaredFields();
        Object result = null;
        try {
            result = clas.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(first);
                Object value2 = field.get(second);
                Object value = (value1 != null) ? value1 : value2;
                field.set(result, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) result;
    }
}
