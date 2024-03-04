package com.socialuni.social.common.sdk.dao.utils;

import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.common.sdk.dao.DO.SocialUserPlatformAccountDO;
import com.socialuni.social.common.sdk.dao.repository.SocialUserPlatformAccountRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialUserAccountUtil {
    private static SocialUserPlatformAccountRepository socialUserPlatformAccountRepository;

    @Resource
    public void setCommonUserAccountRepository(SocialUserPlatformAccountRepository socialUserPlatformAccountRepository) {
        SocialUserAccountUtil.socialUserPlatformAccountRepository = socialUserPlatformAccountRepository;
    }

    public static boolean mineBindSocialuniAccount() {
        return SocialUserAccountUtil.getMineAccountByProvider(SocialuniAccountProviderType.socialuni) != null;
    }

    public static SocialUserPlatformAccountDO getMineSocialAccount() {
        return SocialUserAccountUtil.getMineAccountByProvider(SocialuniAccountProviderType.socialuni);
    }

    public static SocialUserPlatformAccountDO getUserSocialAccount(Integer userId) {
        return SocialUserAccountUtil.getUserAccountByProvider(SocialuniAccountProviderType.socialuni, userId);
    }

    public static SocialUserPlatformAccountDO getMineAccountByProvider(String provider) {
        SocialUserPlatformAccountDO socialUserAccountDO = socialUserPlatformAccountRepository.findByProviderAndUserId(provider, SocialuniUserUtil.getMineUserIdNotNull());
        return socialUserAccountDO;
    }

    public static SocialUserPlatformAccountDO getUserAccountByProvider(String provider, Integer userId) {
        SocialUserPlatformAccountDO socialUserAccountDO = socialUserPlatformAccountRepository.findByProviderAndUserId(provider, userId);
        return socialUserAccountDO;
    }

}
