package com.socialuni.social.common.sdk.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class JavaReflectUtil {
    public static List<Object> getClassStaticFinalFieldValues(Class tClass) {
        Field[] fields = tClass.getFields();

        List<Object> fieldValues = new ArrayList<>();

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            // 判断该属性是否是public final static 类型的
            // 如果想过去其它的,具体可以参考 Modifier 这个类里面的修饰符解码
            boolean flag = (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
            if (flag) {
                String name = field.getName();
                Object value = null;
                try {
                    value = field.get(null);
                    fieldValues.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return fieldValues;
    }

}
