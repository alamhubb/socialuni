package com.socialuni.center.web.redis;

import com.socialuni.center.web.redis.redisKey.RedisKeysConst;

/**
 * redisTemplate封装
 */
public class RedisKeysUtil {

    public static String getRedisKeys(String key, String values) {
        return key + RedisKeysConst.springKey + values;
    }

}