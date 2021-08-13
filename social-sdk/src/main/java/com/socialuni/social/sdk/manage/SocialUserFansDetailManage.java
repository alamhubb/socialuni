package com.socialuni.social.sdk.manage;

import com.socialuni.entity.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.social.sdk.repository.SocialUserFansDetailRepository;
import com.socialuni.entity.model.DO.user.UserDO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserFansDetailManage {
    @Resource
    SocialUserFansDetailRepository socialUserFansDetailRepository;

    public SocialUserFansDetailDO getOrCreateUserFollowDetail(UserDO mineUser) {
        SocialUserFansDetailDO socialUserFansDetailDO = socialUserFansDetailRepository.findByUserId(mineUser.getId());
        if (socialUserFansDetailDO == null) {
            socialUserFansDetailDO = new SocialUserFansDetailDO(mineUser);
            socialUserFansDetailDO = socialUserFansDetailRepository.save(socialUserFansDetailDO);
        }
        return socialUserFansDetailDO;
    }

    public SocialUserFansDetailDO createUserDetailFollow(UserDO mineUser) {
        SocialUserFansDetailDO socialUserFansDetailDO = new SocialUserFansDetailDO(mineUser);
        socialUserFansDetailDO = socialUserFansDetailRepository.save(socialUserFansDetailDO);
        return socialUserFansDetailDO;
    }

    @Async
    public void mineFollowNumAdd(Integer mineUserId) {
        SocialUserFansDetailDO mineUserFollow = socialUserFansDetailRepository.findByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() + 1);
        socialUserFansDetailRepository.save(mineUserFollow);
    }

    @Async
    public void userFansNumAdd(Integer userId) {
        SocialUserFansDetailDO userFollow = socialUserFansDetailRepository.findByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() + 1);
        socialUserFansDetailRepository.save(userFollow);
    }

    @Async
    public void mineFollowNumSub(Integer mineUserId) {
        SocialUserFansDetailDO mineUserFollow = socialUserFansDetailRepository.findByUserId(mineUserId);
        mineUserFollow.setFollowNum(mineUserFollow.getFollowNum() - 1);
        socialUserFansDetailRepository.save(mineUserFollow);
    }

    @Async
    public void userFansNumSub(Integer userId) {
        SocialUserFansDetailDO userFollow = socialUserFansDetailRepository.findByUserId(userId);
        userFollow.setFansNum(userFollow.getFansNum() - 1);
        socialUserFansDetailRepository.save(userFollow);
    }
}
