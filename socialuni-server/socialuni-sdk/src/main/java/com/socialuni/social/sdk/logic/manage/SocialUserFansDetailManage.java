package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.sdk.dao.redis.UserFansDetailRedis;
import com.socialuni.social.user.sdk.model.DO.SocialUserFansDetailDo;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserFansDetailManage {
    @Resource
    private UserFansDetailRedis userFansDetailRedis;

    public SocialUserFansDetailDo getOrCreateUserFollowDetail(SocialuniUserDo mineUser) {
        SocialUserFansDetailDo SocialUserFansDetailDo = userFansDetailRedis.findUserFansDetailByUserId(mineUser.getUnionId());
        if (SocialUserFansDetailDo == null) {
            SocialUserFansDetailDo = new SocialUserFansDetailDo(mineUser);
            SocialUserFansDetailDo = userFansDetailRedis.save(SocialUserFansDetailDo);
        }
        return SocialUserFansDetailDo;
    }

    public SocialUserFansDetailDo createUserDetailFollow(SocialuniUserDo mineUser) {
        SocialUserFansDetailDo SocialUserFansDetailDo = new SocialUserFansDetailDo(mineUser);
        SocialUserFansDetailDo = userFansDetailRedis.save(SocialUserFansDetailDo);
        return SocialUserFansDetailDo;
    }

    @Async
    public void mineFollowNumAdd(Integer mineUserId) {
        SocialUserFansDetailDo mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() + 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumAdd(Integer userId) {
        SocialUserFansDetailDo userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() + 1);
        userFansDetailRedis.save(userFollow);
    }

    @Async
    public void mineFollowNumSub(Integer mineUserId) {
        SocialUserFansDetailDo mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() - 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumSub(Integer userId) {
        SocialUserFansDetailDo userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() - 1);
        userFansDetailRedis.save(userFollow);
    }
}
