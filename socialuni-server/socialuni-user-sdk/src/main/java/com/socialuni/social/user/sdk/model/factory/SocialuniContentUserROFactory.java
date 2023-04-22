package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.user.sdk.constant.UserIdentityAuthStatus;
import com.socialuni.social.user.sdk.logic.manage.SocialuniUserFollowManage;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialUserIdentityAuthDO;
import com.socialuni.social.user.sdk.repository.SocialUserIdentityAuthRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SocialuniContentUserROFactory {
    static SocialuniUserFollowManage followManage;

    static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;

    @Resource
    public void setSocialUserIdentityAuthRepository(SocialUserIdentityAuthRepository socialUserIdentityAuthRepository) {
        SocialuniContentUserROFactory.socialUserIdentityAuthRepository = socialUserIdentityAuthRepository;
    }

    @Resource
    public void setFollowManage(SocialuniUserFollowManage followManage) {
        SocialuniContentUserROFactory.followManage = followManage;
    }

    public static SocialuniContentUserRO newContentUserRO(SocialuniUserDo user, SocialuniUserDo mineUser) {
        SocialuniContentUserRO userRO = new SocialuniContentUserRO(SocialuniUserROFactory.getUserRO(user, mineUser));

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

    public static List<SocialuniContentUserRO> toList(List<SocialuniUserDo> socialuniUserDos, SocialuniUserDo mineUser) {
        return socialuniUserDos.stream().map(item -> SocialuniContentUserROFactory.newContentUserRO(item, mineUser)).collect(Collectors.toList());
    }
}
