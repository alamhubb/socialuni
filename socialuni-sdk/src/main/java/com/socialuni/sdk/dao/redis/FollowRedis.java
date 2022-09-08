package com.socialuni.sdk.dao.redis;

import com.socialuni.sdk.dao.redis.redisKey.RedisKeysConst;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.dao.DO.FollowDO;
import com.socialuni.sdk.dao.repository.FollowRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
@Component
public class FollowRedis {
    @Resource
    FollowRepository followRepository;

    @Cacheable(cacheNames = RedisKeysConst.queryUserFollowUserIds, key = "#userId")
    public List<Integer> queryUserFollowUserIds(Integer userId) {
        return followRepository.queryUserFollowUserIds(userId, CommonStatus.enable);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryUserAndBeUserFollow, key = "#userId+'-'+#beUserId")
    public FollowDO findFirstByUserIdAndBeUserId(Integer userId, Integer beUserId) {
        return followRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
    }

}


