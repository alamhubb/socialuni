package com.socialuni.social.follow.facotry;

import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.follow.logic.manage.SocialuniUserFollowManage;
import com.socialuni.social.follow.model.follow.SocialuniUserFollowRelationInfoRO;
import com.socialuni.social.follow.utils.SocialuniUserFollowInfoUtil;
import com.socialuni.social.identity.dao.DO.SocialUserIdentityAuthDO;
import com.socialuni.social.identity.dao.repository.SocialUserIdentityAuthRepository;
import com.socialuni.social.user.sdk.constant.UserIdentityAuthStatus;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserShowROFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
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
        SocialuniContentUserRO userRO = new SocialuniContentUserRO(SocialuniUserShowROFactory.getUserRO(user, mineUser));

        userRO.setIdentityAuth(false);

        if (mineUser != null && user.getUnionId().equals(mineUser.getUnionId())) {
            userRO.setIsMine(true);
        } else {
            userRO.setIsMine(false);
        }

        Long mineUserId = Optional.ofNullable(mineUser)
                .map(SocialuniUserDo::getUserId)
                .orElse(null);

        SocialuniUserFollowRelationInfoRO socialuniUserFollowRelationInfoRO = SocialuniUserFollowInfoUtil.getUserFollowRelationInfo(user.getUserId(), mineUserId);
        userRO.setHasBeFollowed(socialuniUserFollowRelationInfoRO.getHasBeFollowed());
        userRO.setHasFollowed(socialuniUserFollowRelationInfoRO.getHasFollowed());


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
