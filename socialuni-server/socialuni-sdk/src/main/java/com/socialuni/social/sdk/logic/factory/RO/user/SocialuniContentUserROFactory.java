package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.social.sdk.dao.DO.user.SocialUserIdentityAuthDO;
import com.socialuni.social.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.social.sdk.logic.manage.FollowManage;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.sdk.dao.DO.keywords.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
