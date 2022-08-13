package com.socialuni.embed.demo;

import com.socialuni.embed.demo.controller.TestTokenRepository;
import com.socialuni.embed.demo.controller.TestTokenSocialuniTokenRepository;
import com.socialuni.embed.demo.controller.TestUserRepository;
import com.socialuni.embed.demo.model.TokenDO;
import com.socialuni.embed.demo.model.TokenSocialuniTokenDO;
import com.socialuni.embed.demo.model.UserDO;
import com.socialuni.sdk.entity.user.SocialUserEntity;
import com.socialuni.sdk.manage.TokenManage;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.web.sdk.config.SocialRequestToken;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class SocialRequestTokenImpl implements SocialRequestToken {
    @Resource
    TestTokenRepository testTokenRepository;
    @Resource
    TestTokenSocialuniTokenRepository testTokenSocialuniTokenRepository;
    @Resource
    SocialUserEntity socialUserEntity;
    @Resource
    TestUserRepository testUserRepository;
    @Resource
    TokenManage tokenManage;

    @Override
    public String getToken(HttpServletRequest request) {
        String token = RequestUtil.getRequestValue("token");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        TokenDO tokenDO = testTokenRepository.findByToken(token);

        if (tokenDO == null) {
            throw new SocialNotLoginException();
        }
        TokenSocialuniTokenDO socialuniTokenDO = testTokenSocialuniTokenRepository.findByTokenId(tokenDO.getId());
        //如果token为null，则证明系统没和社交联盟关联，则需要关联
        if (socialuniTokenDO == null) {
            UserDO userDO = testUserRepository.findOneById(tokenDO.getUserId());

            SocialProviderLoginQO loginQO = new SocialProviderLoginQO(userDO.getName());
            SocialUserDO socialUserDO = socialUserEntity.createUserAndDetail(loginQO);
            //创建联盟token
            SocialTokenDO socialUserTokenDO = tokenManage.create(socialUserDO.getUnionId());
            //关联本系统和联盟的token
            socialuniTokenDO = new TokenSocialuniTokenDO(tokenDO.getId(), socialUserTokenDO.getToken());
            testTokenSocialuniTokenRepository.save(socialuniTokenDO);
        }
        return socialuniTokenDO.getSocialuniToken();
    }
}
