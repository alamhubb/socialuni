package com.socialuni.social.follow.dao.redis;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.qingchi.qing.constant.SocialuniCommonStatus;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.follow.dao.DO.SocialuniUserFollowDO;
import com.socialuni.social.follow.dao.repositroy.SocialuniFollowRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
@Component
public class SocialuniUserFollowRedis {
    @Resource
    SocialuniFollowRepository followRepository;

    @Cacheable(cacheNames = CommonRedisKey.queryUserFollowUserIds, key = "#userId")
    public List<Long> queryUserFollowUserIds(Long userId) {
        return followRepository.queryUserFollowUserIds(userId, SocialuniCommonStatus.enable);
    }

    //    @Cacheable(cacheNames = CommonRedisKey.queryUserAndBeUserFollow, key = "#userId+'-'+#beUserId")
    public SocialuniUserFollowDO findFirstByUserIdAndBeUserId(Long userId, Long beUserId) {
//        return followRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
        return SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(userId, beUserId, SocialuniUserFollowDO.class);
    }

}


