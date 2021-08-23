package com.socialuni.social.sdk.domain.phone;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.sdk.constant.ProviderLoginType;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.sdk.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialBindUserSocialuniAccountDomain {
    @Resource
    SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;


    public void bindUserSocialAccount(Integer mineUserId, SocialLoginRO<CenterMineUserDetailRO> socialLoginRO) {
        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(ProviderLoginType.socialuni);
        loginQO.setPlatform(PlatformType.mp);
        loginQO.setCode(socialLoginRO.getToken());

        CenterMineUserDetailRO centerUserDetailRO = socialLoginRO.getUser();

        loginQO.setUnionId(centerUserDetailRO.getId());
        loginQO.setOpenId(centerUserDetailRO.getId());

        socialBindUserProviderAccountEntity.bindProviderAccount(mineUserId, loginQO);
    }

    @Async
    public void bindUserSocialAccount(Integer mineUserId, String token, String userUid) {
        SocialProviderLoginQO loginQO = new SocialProviderLoginQO();
        loginQO.setProvider(ProviderLoginType.socialuni);
        loginQO.setPlatform(PlatformType.mp);
        loginQO.setCode(token);

        loginQO.setUnionId(userUid);
        loginQO.setOpenId(userUid);

        socialBindUserProviderAccountEntity.bindProviderAccount(mineUserId, loginQO);
    }
}