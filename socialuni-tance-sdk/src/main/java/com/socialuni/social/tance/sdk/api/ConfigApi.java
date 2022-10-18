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
//    List<AppConfigDO> findAllByDevIdAndStatusOrderByCreateTimeDesc(Integer devId, Integer status);

    /**
     * 获得模型
     *
     * @param devId
     * @param key
     * @return
     */
    String getString(Integer devId, String key);

    /**
     * 获得模型
     *
     * @param devId
     * @param key
     * @return
     * @see #getString(Integer, String)
     */
    default Boolean getBoolean(Integer devId, String key) {
        return Boolean.parseBoolean(this.getString(devId, key));
    }

    /**
     * 获得模型
     *
     * @param devId
     * @param key
     * @return
     * @see #getString(Integer, String)
     */
    default Integer getInteger(Integer devId, String key) {
        return Integer.valueOf(this.getString(devId, key));
    }

    /**
     * 获得模型
     *
     * @param devId
     * @param key
     * @return
     * @see #getString(Integer, String)
     */
    default String[] getArrayString(Integer devId, String key) {
        java.lang.String string = this.getString(devId, key);
        return string.split(",");
    }

    /**
     * 获得模型
     *
     * @param devId
     * @param key
     * @return
     * @see #getArrayString(Integer, String)
     */
    default List<String> getListString(Integer devId, String key) {
        String[] listString = this.getArrayString(devId, key);
        return Arrays.asList(listString);
    }

    /**
     * 获得模型
     *
     * @param devId
     * @param key
     * @return
     * @see #getListString(Integer, String)
     */
    default List<Integer> getListInteger(Integer devId, String key) {
        try {
            return this.getListString(devId, key).stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (Exception exception) {
            return null; // 报java.lang.NumberFormatException错就是null的默认值。
        }
    }
}