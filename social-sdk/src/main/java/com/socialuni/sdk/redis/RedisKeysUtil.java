package com.socialuni.sdk.redis;

/**
 * redisTemplate封装
 */
public class RedisKeysUtil {

    public static String getRedisKeys(String key, String values) {
        return key + RedisKeysConst.springKey + values;
    }

}