package com.socialuni.sdk.redis;

import com.socialuni.sdk.redis.redisKey.RedisKeysConst;

/**
 * redisTemplate封装
 */
public class RedisKeysUtil {

    public static String getRedisKeys(String key, String values) {
        return key + RedisKeysConst.springKey + values;
    }

}