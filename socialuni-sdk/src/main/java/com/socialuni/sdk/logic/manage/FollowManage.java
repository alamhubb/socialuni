package com.socialuni.sdk.logic.manage;

import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.dao.DO.FollowDO;
import com.socialuni.sdk.dao.repository.FollowRepository;
import com.socialuni.sdk.dao.redis.FollowRedis;
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
        FollowDO followDO = new FollowDO();
        //两个用户粉丝和关注各加1
        followDO.setUserId(mineUserId);
        followDO.setBeUserId(beUserId);
        followDO = followRepository.save(followDO);
    }

    @Async
    public void updateFollow(FollowDO followDO, String status) {
        followDO.setStatus(status);
        followDO.setUpdateTime(new Date());
        followDO = followRepository.save(followDO);
    }

    public boolean userHasFollowBeUser(Integer userId, Integer beUserId) {
        FollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(userId, beUserId);
        return followDO != null && CommonStatus.enable.equals(followDO.getStatus());
    }
}
