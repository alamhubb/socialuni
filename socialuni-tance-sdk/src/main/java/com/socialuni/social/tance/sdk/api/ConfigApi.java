package com.socialuni.social.tance.sdk.api;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/15 22:47
 * @since 1.0
 */
public interface ConfigApi {
    // 该方法属于特有的方法提供。
//    List<AppConfigDO> findAllByDevIdAndStatusOrderByCreateTimeDesc( Integer status);

    /**
     * 获得模型
     *
     *
     * @param key
     * @return
     */
    String getString( String key);

    /**
     * 获得模型
     *
     * 
     * @param key
     * @return
     * @see #getString( String)
     */
    default Boolean getBoolean( String key) {
        return Boolean.parseBoolean(this.getString( key));
    }

    /**
     * 获得模型
     *
     * 
     * @param key
     * @return
     * @see #getString( String)
     */
    default Integer getInteger( String key) {
        return Integer.valueOf(this.getString( key));
    }

    /**
     * 获得模型
     *
     * 
     * @param key
     * @return
     * @see #getString( String)
     */
    default String[] getArrayString( String key) {
        java.lang.String string = this.getString( key);
        return string.split(",");
    }

    /**
     * 获得模型
     *
     * 
     * @param key
     * @return
     * @see #getArrayString( String)
     */
    default List<String> getListString( String key) {
        String[] listString = this.getArrayString( key);
        return Arrays.asList(listString);
    }

    /**
     * 获得模型
     *
     * 
     * @param key
     * @return
     * @see #getListString( String)
     */
    default List<Integer> getListInteger( String key) {
        try {
            return this.getListString( key).stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (Exception exception) {
            return null; // 报java.lang.NumberFormatException错就是null的默认值。
        }
    }
}