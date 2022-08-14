package com.socialuni.sdk.utils.model;

import com.socialuni.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.sdk.model.DO.user.SocialUserAccountDO;
import com.socialuni.sdk.repository.user.SocialUserAccountRepository;
import com.socialuni.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialUserAccountUtil {
    private static SocialUserAccountRepository socialUserAccountRepository;

    @Resource
    public void setCommonUserAccountRepository(SocialUserAccountRepository socialUserAccountRepository) {
        SocialUserAccountUtil.socialUserAccountRepository = socialUserAccountRepository;
    }

    public static boolean mineBindSocialuniAccount() {
        return SocialUserAccountUtil.getMineAccountByProvider(SocialuniAccountProviderType.socialuni) != null;
    }

    public static SocialUserAccountDO getMineSocialAccount() {
        return SocialUserAccountUtil.getMineAccountByProvider(SocialuniAccountProviderType.socialuni);
    }

    public static SocialUserAccountDO getUserSocialAccount(Integer userId) {
        return SocialUserAccountUtil.getUserAccountByProvider(SocialuniAccountProviderType.socialuni, userId);
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
