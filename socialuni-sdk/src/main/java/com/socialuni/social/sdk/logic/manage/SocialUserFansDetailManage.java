package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.user.sdk.model.SocialUserFansDetailModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.dao.redis.UserFansDetailRedis;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserFansDetailManage {
    @Resource
    private UserFansDetailRedis userFansDetailRedis;

    public SocialUserFansDetailModel getOrCreateUserFollowDetail(SocialuniUserModel mineUser) {
        SocialUserFansDetailModel socialUserFansDetailModel = userFansDetailRedis.findUserFansDetailByUserId(mineUser.getUnionId());
        if (socialUserFansDetailModel == null) {
            socialUserFansDetailModel = new SocialUserFansDetailModel(mineUser);
            socialUserFansDetailModel = userFansDetailRedis.save(socialUserFansDetailModel);
        }
        return socialUserFansDetailModel;
    }

    public SocialUserFansDetailModel createUserDetailFollow(SocialuniUserModel mineUser) {
        SocialUserFansDetailModel socialUserFansDetailModel = new SocialUserFansDetailModel(mineUser);
        socialUserFansDetailModel = userFansDetailRedis.save(socialUserFansDetailModel);
        return socialUserFansDetailModel;
    }

    @Async
    public void mineFollowNumAdd(Integer mineUserId) {
        SocialUserFansDetailModel mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() + 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumAdd(Integer userId) {
        SocialUserFansDetailModel userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() + 1);
        userFansDetailRedis.save(userFollow);
    }

    @Async
    public void mineFollowNumSub(Integer mineUserId) {
        SocialUserFansDetailModel mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() - 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumSub(Integer userId) {
        SocialUserFansDetailModel userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() - 1);
        userFansDetailRedis.save(userFollow);
    }
}
