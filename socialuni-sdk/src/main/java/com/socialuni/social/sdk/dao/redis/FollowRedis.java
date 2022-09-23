package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.sdk.dao.redis.redisKey.CommonRedisKey;
import com.socialuni.social.sdk.constant.socialuni.CommonStatus;
import com.socialuni.social.sdk.dao.DO.FollowDO;
import com.socialuni.social.sdk.dao.repository.FollowRepository;
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

    @Cacheable(cacheNames = CommonRedisKey.queryUserFollowUserIds, key = "#userId")
    public List<Integer> queryUserFollowUserIds(Integer userId) {
        return followRepository.queryUserFollowUserIds(userId, CommonStatus.enable);
    }

    @Cacheable(cacheNames = CommonRedisKey.queryUserAndBeUserFollow, key = "#userId+'-'+#beUserId")
    public FollowDO findFirstByUserIdAndBeUserId(Integer userId, Integer beUserId) {
        return followRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
    }

}


