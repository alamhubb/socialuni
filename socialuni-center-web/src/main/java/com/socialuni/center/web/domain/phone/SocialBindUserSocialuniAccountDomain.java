package com.socialuni.center.web.domain.phone;

import com.socialuni.center.web.constant.SocialuniProviderLoginType;
import com.socialuni.center.web.constant.platform.PlatformType;
import com.socialuni.center.web.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
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
        loginQO.setProvider(SocialuniProviderLoginType.socialuni);
        loginQO.setPlatform(PlatformType.mp);
        loginQO.setCode(token);

        loginQO.setUnionId(userUid);
        loginQO.setOpenId(userUid);

        socialBindUserProviderAccountEntity.bindProviderAccount(mineUserId, loginQO);
    }
}