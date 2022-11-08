package com.socialuni.social.sdk.dao.redis;

/**
 * redisTemplate封装
 */
public class RedisKeysUtil {
    //系统
    public static final String springKey = "::";

    public static String getRedisKeys(String key, String values) {
        return key + RedisKeysUtil.springKey + values;
    }

}