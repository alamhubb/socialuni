package com.socialuni.sdk.manage;

import com.socialuni.entity.model.DO.FollowDO;
import com.socialuni.sdk.repository.FollowRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class FollowManage {
    @Resource
    private FollowRepository followRepository;

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
}
