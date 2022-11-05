package com.socialuni.social.sdk.logic.domain.phone;

import com.socialuni.social.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.sdk.logic.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialBindUserSocialuniAccountDomain {
    @Resource
    SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;


    /*public void bindUserSocialAccount(Integer mineUserId, SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO) {
        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(SocialuniProviderLoginType.socialuni);
        loginQO.setPlatform(PlatformType.mp);
        loginQO.setCode(socialLoginRO.getToken());

        SocialuniMineUserDetailRO centerUserDetailRO = socialLoginRO.getUser();

        loginQO.setUnionId(centerUserDetailRO.getId());
        loginQO.setOpenId(centerUserDetailRO.getId());

        socialBindUserProviderAccountEntity.bindProviderAccount(mineUserId, loginQO);
    }
*/
    @Async
    public void bindOrUpdateUserSocialAccount(Integer mineUserId, String token, String userUid) {
        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(SocialuniAccountProviderType.socialuni);
        loginQO.setPlatform(PlatformType.mp);
        loginQO.setCode(token);

        loginQO.setUnionId(userUid);
        loginQO.setOpenId(userUid);

        socialBindUserProviderAccountEntity.bindOrUpdateProviderAccount(mineUserId, loginQO);
    }
}