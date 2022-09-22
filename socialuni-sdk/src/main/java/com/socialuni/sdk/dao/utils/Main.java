package com.socialuni.sdk.dao.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Field[] fields = Person.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getGenericType() instanceof ParameterizedType) {
                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                Type[] arguments = genericType.getActualTypeArguments();
                for (Type type : arguments) {
                    System.out.println(type);
                }
            }
        }
    }
}