package com.socialuni.embed.demo.service;

import com.socialuni.embed.demo.controller.TestTokenRepository;
import com.socialuni.embed.demo.controller.TestTokenSocialuniTokenRepository;
import com.socialuni.embed.demo.model.TokenDO;
import com.socialuni.embed.demo.model.TokenSocialuniTokenDO;
import com.socialuni.sdk.domain.login.SocialProviderLoginDomain;
import com.socialuni.sdk.entity.user.SocialUserEntity;
import com.socialuni.sdk.factory.user.base.SocialUserROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    TestTokenRepository testTokenRepository;
    @Resource
    TestTokenSocialuniTokenRepository testTokenSocialuniTokenRepository;
    @Resource
    SocialUserEntity socialUserEntity;
    @Resource
    SocialProviderLoginDomain socialProviderLoginDomain;


    public SocialUserRO getSocialUserDOByLogin(String name) {
        String token = RequestUtil.getHeader("token");
        TokenDO tokenDO = testTokenRepository.findByToken(token);

        if (tokenDO == null) {
            return null;
        }
        TokenSocialuniTokenDO socialuniTokenDO = testTokenSocialuniTokenRepository.findByTokenId(tokenDO.getId());

        String socialuniToken;
        //如果token为null，则证明系统没和社交联盟关联，则需要关联
        if (socialuniTokenDO == null) {
            SocialProviderLoginQO loginQO = new SocialProviderLoginQO(name);
            SocialUserDO socialUserDO = socialUserEntity.createUserAndDetail(loginQO);

            SocialLoginRO<SocialMineUserDetailRO> socialMineUserDetailROSocialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);
            socialuniToken = socialMineUserDetailROSocialLoginRO.getToken();

            socialuniTokenDO = new TokenSocialuniTokenDO(tokenDO.getId(), socialuniToken);

            testTokenSocialuniTokenRepository.save(socialuniTokenDO);
        }

        socialuniToken = socialuniTokenDO.getSocialuniToken();

        SocialUserRO socialUserRO = SocialUserROFactory.getUserRO(SocialUserUtil.getMineUserNotNull(socialuniToken));

        //根据这个token，找到联盟的token。

        return socialUserRO;
    }


}
