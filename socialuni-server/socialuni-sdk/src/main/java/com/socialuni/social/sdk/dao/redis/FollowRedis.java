package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserFollowDO;
import com.socialuni.social.user.sdk.repository.SocialuniFollowRepository;
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
    SocialuniFollowRepository followRepository;

    @Cacheable(cacheNames = CommonRedisKey.queryUserFollowUserIds, key = "#userId")
    public List<Integer> queryUserFollowUserIds(Integer userId) {
        return followRepository.queryUserFollowUserIds(userId, SocialuniCommonStatus.enable);
    }

    @Cacheable(cacheNames = CommonRedisKey.queryUserAndBeUserFollow, key = "#userId+'-'+#beUserId")
    public SocialuniUserFollowDO findFirstByUserIdAndBeUserId(Integer userId, Integer beUserId) {
        return followRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
    }

}


