package com.socialuni.sdk.dao.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseDao<T> {
    // 用来接收子类的泛型的类型
    private Class<T> type;

    {
        // 子类对象被建立时，会调用父类的构造代码块
        // 这里的this指向了子类对象
        // 获取子类的Class对象
        Class clazz = this.getClass();
        // 获取带泛型的父类的ParameterizedType对象
        ParameterizedType superclass = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取泛型参数数组
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        // 该数组的第一个值就是T(也就是子类的Class类型)
        type = (Class<T>) actualTypeArguments[0];
        System.out.println(type);
    }
}