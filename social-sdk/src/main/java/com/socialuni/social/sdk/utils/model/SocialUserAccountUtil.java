package com.socialuni.social.sdk.utils.model;

import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.sdk.utils.CommonUserAccountUtil;
import com.socialuni.social.sdk.constant.ProviderLoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocialUserAccountUtil {

    public static SocialUserAccountDO getMineSocialAccount() {
        return CommonUserAccountUtil.getMineAccountByProvider(ProviderLoginType.socialuni);
    }

    public static SocialUserAccountDO getUserSocialAccount(Integer userId) {
        return CommonUserAccountUtil.getUserAccountByProvider(ProviderLoginType.socialuni, userId);
    }
}
