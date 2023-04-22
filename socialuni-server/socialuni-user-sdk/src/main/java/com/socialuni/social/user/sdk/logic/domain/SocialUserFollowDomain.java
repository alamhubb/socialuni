package com.socialuni.social.user.sdk.logic.domain;

import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.manage.SocialuniUserFollowManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.user.sdk.logic.redis.SocialuniUserFollowRedis;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserFollowDO;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.model.QO.follow.SocialuniUserFollowAddQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class SocialUserFollowDomain implements SocialUserFollowDomainInterface {
    @Resource
    private SocialuniUserFollowRedis followRedis;
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;
    @Resource
    SocialuniUserFollowManage followManage;

    @Transactional
    public SocialuniUserFollowDO addFlow(SocialuniUserFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getBeUserId());
        if (beUserId.equals(mineUserId)) {
            throw new SocialParamsException("不能自己关注自己哦");
        }
        boolean hasFollow = followManage.userHasFollowBeUser(mineUserId, beUserId);
        if (hasFollow) {
            throw new SocialParamsException("已经关注过此用户了");
        }
        socialUserFansDetailManage.mineFollowNumAdd(mineUserId);
        socialUserFansDetailManage.beUserFansNumAdd(beUserId);
        SocialuniUserFollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        //未关注过
        if (followDO == null) {
            followDO = followManage.createFollow(mineUserId, beUserId);
        } else {
            //已经关注
            followDO = followManage.updateFollow(followDO, SocialuniCommonStatus.enable);
        }
        return followDO;
    }

    public void cancelFollow(SocialuniUserFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getBeUserId());
        if (beUserId.equals(mineUserId)) {
            throw new SocialParamsException("不能自己取消关注自己哦");
        }
        boolean hasFollow = followManage.userHasFollowBeUser(mineUserId, beUserId);
        if (!hasFollow) {
            throw new SocialParamsException("并没有关注此用户");
        }
        socialUserFansDetailManage.mineFollowNumSub(mineUserId);
        socialUserFansDetailManage.beUserFansNumSub(beUserId);
        SocialuniUserFollowDO followDO = followRedis.findFirstByUserIdAndBeUserId(mineUserId, beUserId);
        followManage.updateFollow(followDO, SocialuniCommonStatus.delete);
    }
}
