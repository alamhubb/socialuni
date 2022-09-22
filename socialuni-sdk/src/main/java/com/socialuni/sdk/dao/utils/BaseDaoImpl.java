package com.socialuni.sdk.dao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.sdk.model.RO.UserOAuthRO;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseDaoImpl<T> {

    // 保存当前运行类的参数化类型中的实际的类型  
    private Class clazz;
    // 表名  
    private String tableName;

    // 构造函数： 1. 获取当前运行类的参数化类型； 2. 获取参数化类型中实际类型的定义(class)  
    public BaseDaoImpl() {
        //  this  表示当前运行类  (假设是AccountDao或AdminDao)  
        //  this.getClass()  当前运行类的字节码(AccountDao.class或AdminDao.class)  
        //  this.getClass().getGenericSuperclass();  当前运行类的父类，即为BaseDaoImpl<Account>  
        //                                           其实就是“参数化类型”， ParameterizedType     
        Type type = this.getClass().getGenericSuperclass();
        // 强制转换为“参数化类型”  【BaseDaoImpl<Account>】
        ParameterizedType pt = (ParameterizedType) type;
        // 获取参数化类型中，实际类型的定义  【new Type[]{Account.class}】  
        Type types[] = pt.getActualTypeArguments();
        // 获取数据的第一个元素：Accout.class  
        clazz = (Class) types[0];
        // 表名  (与类名一样，只要获取类名就可以)  
        tableName = clazz.getSimpleName();
    }

    public static void main(String[] args){
        BaseDaoImpl<UserOAuthRO> baseDao = new BaseDaoImpl<>();
    }
}