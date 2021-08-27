package com.socialuni.social.sdk.utils;

import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.sdk.repository.SocialUserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CommonUserAccountUtil {
    private static SocialUserAccountRepository socialUserAccountRepository;

    @Resource
    public void setCommonUserAccountRepository(SocialUserAccountRepository socialUserAccountRepository) {
        CommonUserAccountUtil.socialUserAccountRepository = socialUserAccountRepository;
    }

    public static SocialUserAccountDO getMineAccountByProvider(String provider) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(provider, SocialUserUtil.getMineUserIdNotNull());
        return socialUserAccountDO;
    }

    public static SocialUserAccountDO getUserAccountByProvider(String provider, Integer userId) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(provider, userId);
        return socialUserAccountDO;
    }
}
