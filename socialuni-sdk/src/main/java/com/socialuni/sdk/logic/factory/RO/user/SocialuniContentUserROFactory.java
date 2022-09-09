package com.socialuni.sdk.logic.factory.RO.user;

import com.socialuni.sdk.logic.manage.FollowManage;
import com.socialuni.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.sdk.dao.DO.user.SocialUserIdentityAuthDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialuniContentUserRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniContentUserROFactory {
    static FollowManage followManage;

    static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;

    @Resource
    public void setSocialUserIdentityAuthRepository(SocialUserIdentityAuthRepository socialUserIdentityAuthRepository) {
        SocialuniContentUserROFactory.socialUserIdentityAuthRepository = socialUserIdentityAuthRepository;
    }

    @Resource
    public void setFollowManage(FollowManage followManage) {
        SocialuniContentUserROFactory.followManage = followManage;
    }

    public static SocialuniContentUserRO newContentUserRO(SocialuniUserDO user, SocialuniUserDO mineUser) {
        SocialuniContentUserRO userRO = new SocialuniContentUserRO(SocialuniListUserROFactory.getListUserRO(user));

        userRO.setHasFollowed(false);
        userRO.setHasBeFollowed(false);
        userRO.setIdentityAuth(false);

        if (mineUser != null && user.getUnionId().equals(mineUser.getUnionId())) {
            userRO.setIsMine(true);
        } else {
            userRO.setIsMine(false);
        }

        if (mineUser != null && !userRO.getIsMine()) {
            boolean hasFollowUser = followManage.userHasFollowBeUser(mineUser.getUnionId(), user.getUnionId());
            userRO.setHasFollowed(hasFollowUser);

            boolean hasBeFollowed = followManage.userHasFollowBeUser(user.getUnionId(), mineUser.getUnionId());
            userRO.setHasBeFollowed(hasBeFollowed);
        }

        SocialUserIdentityAuthDO socialUserIdentityAuthDO = socialUserIdentityAuthRepository.findFirstByUserId(user.getUnionId());
        if (socialUserIdentityAuthDO != null) {
            if (UserIdentityAuthStatus.authSuccessList.contains(socialUserIdentityAuthDO.getStatus())) {
                userRO.setIdentityAuth(true);
            }
        }
        return userRO;
    }

}
