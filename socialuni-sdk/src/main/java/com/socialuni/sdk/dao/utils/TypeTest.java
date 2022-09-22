package com.socialuni.sdk.dao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.sdk.model.RO.UserOAuthRO;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeTest<N> {
    public static <T> T getTname() {
        TypeTest<T> typeTest1 = new TypeTest<T>() {
        };
        typeTest1.testT();
        return null;
    }


    public static void main(String[] args) throws JsonProcessingException {
        UserOAuthRO userOAuthRO = getTname();
    }


    public N testT() {
        ParameterizedType genericType = ((ParameterizedType) this.getClass().getGenericSuperclass());
        Type[] arguments = genericType.getActualTypeArguments();
        for (Type type : arguments) {
            System.out.println(type);
            System.out.println(type.getTypeName());
            System.out.println(type.getClass().getSimpleName());
        }
        /*Class clazz = (Class) arguments[0];
        System.out.println(clazz.getSimpleName());*/
        return null;
        /*Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        System.out.println(actualTypeArguments);

        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        Type[] arguments = genericType.getActualTypeArguments();
        for (Type type : arguments) {
            System.out.println(type);
        }
        return name;*/
    }
}