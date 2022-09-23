package com.socialuni.sdk.dao.redis;

import com.socialuni.sdk.dao.redis.redisKey.CommonRedisKey;

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