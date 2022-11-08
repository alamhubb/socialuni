package com.socialuni.social.common.api.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/13 18:11
 * @since 1.0
 */
public class MapUtil extends cn.hutool.core.map.MapUtil {

    /**
     * 行转列
     * @return
     */
    public static Map<String,Object>  pivot(List<?> list,String keyName,String valueName){
        if(list == null) return null;
        Map<String,Object> map = new HashMap<>();
        list.forEach((obj)->{
            Map<String, Object> x = BeanUtil.beanToMap(obj);
            map.put(String.valueOf(x.get(keyName)),x.get(valueName));
        });
        return map;
    }
}
