package com.socialuni.social.follow.logic.manage;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.follow.dao.DO.SocialUserFansDetailDo;
import com.socialuni.social.follow.dao.redis.UserFansDetailRedis;
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
    public void mineFollowNumAdd(Long mineUserId) {
        SocialUserFansDetailDo mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() + 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumAdd(Long userId) {
        SocialUserFansDetailDo userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() + 1);
        userFansDetailRedis.save(userFollow);
    }

    @Async
    public void mineFollowNumSub(Long mineUserId) {
        SocialUserFansDetailDo mineUserFollow = userFansDetailRedis.findUserFansDetailByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() - 1);
        userFansDetailRedis.save(mineUserFollow);
    }

    @Async
    public void beUserFansNumSub(Long userId) {
        SocialUserFansDetailDo userFollow = userFansDetailRedis.findUserFansDetailByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() - 1);
        userFansDetailRedis.save(userFollow);
    }
}
