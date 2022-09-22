package com.socialuni.sdk.logic.factory;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ListConvertUtil {
    //list转换，TO类List转为RO类List
    public static <TO, RO> List<RO> toList(Function<TO, RO> function, List<TO> list) {
        return list.stream().map(function).collect(Collectors.toList());
    }

    //list转换，TO类List转为RO类List,支持额外传入一个参数
    public static <TO, U, RO> List<RO> toList(BiFunction<TO, U, RO> function, List<TO> list, U user) {
        return list.stream().map(t -> function.apply(t, user)).collect(Collectors.toList());
    }

    //list转换，TO类List转为RO类List,支持额外传入一个参数
    public static <Type> List<Type> intersection(List<Type> listA, List<Type> listB) {
        Map<Type, Boolean> mapA = new LinkedHashMap<>();
        Map<Type, Boolean> mapB = new LinkedHashMap<>();

        for (Type type : listA) {
            mapA.put(type, true);
        }
        for (Type type : listB) {
            mapB.put(type, true);
        }

        List<Type> res = new ArrayList<>();
        for (Type keyA : mapA.keySet()) {
            if (mapB.containsKey(keyA)) {
                res.add(keyA);
            }
        }
        return  res;
    }
}