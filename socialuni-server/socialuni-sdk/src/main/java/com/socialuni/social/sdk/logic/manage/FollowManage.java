package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.sdk.dao.DO.SocialuniFollowDO;
import com.socialuni.social.sdk.dao.repository.FollowRepository;
import com.socialuni.social.sdk.dao.redis.FollowRedis;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class FollowManage {
    @Resource
    private FollowRepository followRepository;
    @Resource
    private FollowRedis followRedis;

    @Async
    public void createFollow(Integer mineUserId, Integer beUserId) {
        SocialuniFollowDO followDO = new SocialuniFollowDO();
        //两个用户粉丝和关注各加1
        followDO.setUserId(mineUserId);
        followDO.setBeUserId(beUserId);
        followDO = followRepository.save(followDO);
    }

    @Async
    public void updateFollow(SocialuniFollowDO followDO, String status) {
        followDO.setStatus(status);
        followDO.setUpdateTime(new Date());
        followDO = followRepository.save(followDO);
    }

    public boolean userHasFollowBeUser(Integer userId, Integer beUserId) {
        SocialuniFollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(userId, beUserId);
        return followDO != null && SocialuniCommonStatus.init.equals(followDO.getStatus());
    }
}
