package com.socialuni.social.tance.sdk.api;


/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/15 22:47
 * @since 1.0
 */
public interface ConfigApi {
    /**
     * 获得模型
     * @param devKey
     * @param key
     * @return
     */
    String getString(String devKey,String key);
    /**
     * 获得模型
     * @param devKey
     * @param key
     * @return
     * @see #getString(String, String)
     */
    default Boolean getBoolean(String devKey,String key){
        return  Boolean.parseBoolean(this.getString(devKey,key));
    }
    /**
     * 获得模型
     * @param devKey
     * @param key
     * @return
     * @see #getString(String, String)
     */
    default Integer getInteger(String devKey,String key){
        return  Integer.valueOf(this.getString(devKey,key));
    }
}
