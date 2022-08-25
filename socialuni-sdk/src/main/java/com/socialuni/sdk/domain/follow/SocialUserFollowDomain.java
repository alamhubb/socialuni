package com.socialuni.sdk.domain.follow;

import com.socialuni.sdk.manage.FollowManage;
import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.sdk.redis.FollowRedis;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.model.DO.FollowDO;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Component
public class SocialUserFollowDomain {
    @Resource
    private FollowRedis followRedis;
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;
    @Resource
    FollowManage followManage;

    public void addFlow(@NotNull Integer mineUserId, @NotNull Integer beUserId) {
        if (beUserId.equals(mineUserId)) {
            throw new SocialParamsException("不能自己关注自己哦");
        }
        boolean hasFollow = followManage.userHasFollowBeUser(mineUserId, beUserId);
        if (hasFollow) {
            throw new SocialParamsException("已经关注过此用户了");
        }
        privateAddFlowAsync(mineUserId, beUserId);
    }

    @Async
    public void privateAddFlowAsync(Integer mineUserId, Integer beUserId) {
        socialUserFansDetailManage.mineFollowNumAdd(mineUserId);
        socialUserFansDetailManage.beUserFansNumAdd(beUserId);
        FollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        //未关注过
        if (followDO == null) {
            followManage.createFollow(mineUserId, beUserId);
        } else {
            //已经关注
            followManage.updateFollow(followDO, CommonStatus.enable);
        }
    }

    public void cancelFollow(@NotNull Integer mineUserId, @NotNull Integer beUserId) {
        if (beUserId.equals(mineUserId)) {
            throw new SocialParamsException("不能自己取消关注自己哦");
        }
        boolean hasFollow = followManage.userHasFollowBeUser(mineUserId, beUserId);
        if (!hasFollow) {
            throw new SocialParamsException("并没有关注此用户");
        }
        privateCancelFollowAsync(mineUserId, beUserId);
    }

    @Async
    public void privateCancelFollowAsync(Integer mineUserId, Integer beUserId) {
        socialUserFansDetailManage.mineFollowNumSub(mineUserId);
        socialUserFansDetailManage.beUserFansNumSub(beUserId);
        FollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        followManage.updateFollow(followDO, CommonStatus.delete);
    }
}
