package com.socialuni.social.sdk.domain.follow;

import com.socialuni.constant.CommonStatus;
import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.social.sdk.manage.FollowManage;
import com.socialuni.social.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.entity.model.DO.FollowDO;
import com.socialuni.social.sdk.repository.FollowRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Component
public class SocialUserFollowDomain {
    @Resource
    private FollowRepository followRepository;
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;
    @Resource
    FollowManage followManage;

    public void addFlow(@NotNull Integer mineUserId, @NotNull Integer beUserId) {
        if (beUserId.equals(mineUserId)) {
            throw new SocialParamsException("不能自己关注自己哦");
        }
        FollowDO followDO = followRepository.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        if (followDO != null && CommonStatus.enable.equals(followDO.getStatus())) {
            throw new SocialParamsException("已经关注过此用户了");
        }
        privateAddFlowAsync(mineUserId, beUserId, followDO);
    }

    @Async
    public void privateAddFlowAsync(Integer mineUserId, Integer beUserId, FollowDO followDO) {
        socialUserFansDetailManage.mineFollowNumAdd(mineUserId);
        socialUserFansDetailManage.userFansNumAdd(mineUserId);
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
        FollowDO followDO = followRepository.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        if (followDO == null || !CommonStatus.enable.equals(followDO.getStatus())) {
            throw new SocialParamsException("并没有关注此用户");
        }
        privateCancelFollowAsync(mineUserId, followDO);
    }

    @Async
    public void privateCancelFollowAsync(Integer mineUserId, FollowDO followDO) {
        socialUserFansDetailManage.mineFollowNumSub(mineUserId);
        socialUserFansDetailManage.userFansNumSub(mineUserId);
        followManage.updateFollow(followDO, CommonStatus.delete);
    }
}
