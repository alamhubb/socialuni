package com.socialuni.social.sdk.manage;

import com.socialuni.social.entity.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.redis.UserFansDetailRedis;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserFansDetailManage {
    @Resource
    private UserFansDetailRedis userFansDetailRedis;

    public SocialUserFansDetailDO getOrCreateUserFollowDetail(UserDO mineUser) {
        SocialUserFansDetailDO socialUserFansDetailDO = userFansDetailRedis.findUserFansDetailByUserId(mineUser.getId());
        if (socialUserFansDetailDO == null) {
            socialUserFansDetailDO = new SocialUserFansDetailDO(mineUser);
            socialUserFansDetailDO = userFansDetailRedis.save(socialUserFansDetailDO);
        }
        return socialUserFansDetailDO;
    }

    public SocialUserFansDetailDO createUserDetailFollow(UserDO mineUser) {
        SocialUserFansDetailDO socialUserFansDetailDO = new SocialUserFansDetailDO(mineUser);
        socialUserFansDetailDO = userFansDetailRedis.save(socialUserFansDetailDO);
        return socialUserFansDetailDO;
    }

    @Async
    public void mineFollowNumAdd(Integer mineUserId) {
        SocialUserFansDetailDO mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() + 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumAdd(Integer userId) {
        SocialUserFansDetailDO userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() + 1);
        userFansDetailRedis.save(userFollow);
    }

    @Async
    public void mineFollowNumSub(Integer mineUserId) {
        SocialUserFansDetailDO mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() - 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumSub(Integer userId) {
        SocialUserFansDetailDO userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() - 1);
        userFansDetailRedis.save(userFollow);
    }
}
