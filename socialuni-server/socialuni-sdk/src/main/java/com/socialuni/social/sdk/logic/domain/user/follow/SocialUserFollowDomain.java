package com.socialuni.social.sdk.logic.domain.user.follow;

import com.socialuni.social.sdk.logic.manage.FollowManage;
import com.socialuni.social.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.dao.redis.FollowRedis;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.sdk.dao.DO.SocialuniFollowDO;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
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

    @Async
    public void addFlow(@NotNull Integer mineUserId, @NotNull Integer beUserId) {
        if (beUserId.equals(mineUserId)) {
            throw new SocialParamsException("不能自己关注自己哦");
        }
        boolean hasFollow = followManage.userHasFollowBeUser(mineUserId, beUserId);
        if (hasFollow) {
            throw new SocialParamsException("已经关注过此用户了");
        }
        socialUserFansDetailManage.mineFollowNumAdd(mineUserId);
        socialUserFansDetailManage.beUserFansNumAdd(beUserId);
        SocialuniFollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        //未关注过
        if (followDO == null) {
            followManage.createFollow(mineUserId, beUserId);
        } else {
            //已经关注
            followManage.updateFollow(followDO, SocialuniCommonStatus.init);
        }
    }

    @Async
    public void cancelFollow(@NotNull Integer mineUserId, @NotNull Integer beUserId) {
        if (beUserId.equals(mineUserId)) {
            throw new SocialParamsException("不能自己取消关注自己哦");
        }
        boolean hasFollow = followManage.userHasFollowBeUser(mineUserId, beUserId);
        if (!hasFollow) {
            throw new SocialParamsException("并没有关注此用户");
        }
        socialUserFansDetailManage.mineFollowNumSub(mineUserId);
        socialUserFansDetailManage.beUserFansNumSub(beUserId);
        SocialuniFollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        followManage.updateFollow(followDO, SocialuniCommonStatus.delete);
    }
}
