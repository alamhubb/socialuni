package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.user.sdk.model.DO.SocialUserAccountDO;
import com.socialuni.social.user.sdk.repository.SocialUserAccountRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
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
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(provider, SocialuniUserUtil.getMineUserIdNotNull());
        return socialUserAccountDO;
    }

    public static SocialUserAccountDO getUserAccountByProvider(String provider, Integer userId) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(provider, userId);
        return socialUserAccountDO;
    }

}
