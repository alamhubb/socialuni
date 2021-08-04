package com.socialuni.sdk.store;

import com.socialuni.sdk.constant.CommonStatus;
import com.socialuni.sdk.redis.RedisKeysConst;
import com.socialuni.sdk.repository.FollowRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
@Component
public class FollowStore {
    @Resource
    FollowRepository followRepository;

    @Cacheable(cacheNames = RedisKeysConst.queryUserFollowUserIds, key = "#userId")
    public List<Integer> queryUserFollowUserIds(Integer userId) {
        return followRepository.queryUserFollowUserIds(userId, CommonStatus.enable);
    }
}


