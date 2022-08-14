package com.socialuni.sdk.domain.phone;

import com.socialuni.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.sdk.constant.platform.PlatformType;
import com.socialuni.sdk.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialBindUserSocialuniAccountDomain {
    @Resource
    SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;


    /*public void bindUserSocialAccount(Integer mineUserId, SocialLoginRO<CenterMineUserDetailRO> socialLoginRO) {
        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(SocialuniProviderLoginType.socialuni);
        loginQO.setPlatform(PlatformType.mp);
        loginQO.setCode(socialLoginRO.getToken());

        CenterMineUserDetailRO centerUserDetailRO = socialLoginRO.getUser();

        loginQO.setUnionId(centerUserDetailRO.getId());
        loginQO.setOpenId(centerUserDetailRO.getId());

        socialBindUserProviderAccountEntity.bindProviderAccount(mineUserId, loginQO);
    }
*/
    @Async
    public void bindUserSocialAccount(Integer mineUserId, String token, String userUid) {
        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(SocialuniAccountProviderType.socialuni);
        loginQO.setPlatform(PlatformType.mp);
        loginQO.setCode(token);

        loginQO.setUnionId(userUid);
        loginQO.setOpenId(userUid);

        socialBindUserProviderAccountEntity.bindProviderAccount(mineUserId, loginQO);
    }
}