package com.socialuni.social.sdk.utils.model;

import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.sdk.utils.CommonUserAccountUtil;
import com.socialuni.social.sdk.constant.SocialuniProviderLoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocialUserAccountUtil {

    public static SocialUserAccountDO getMineSocialAccount() {
        return CommonUserAccountUtil.getMineAccountByProvider(SocialuniProviderLoginType.socialuni);
    }

    public static SocialUserAccountDO getUserSocialAccount(Integer userId) {
        return CommonUserAccountUtil.getUserAccountByProvider(SocialuniProviderLoginType.socialuni, userId);
    }
}
