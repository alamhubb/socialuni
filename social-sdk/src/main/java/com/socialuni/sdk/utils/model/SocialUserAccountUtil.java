package com.socialuni.sdk.utils.model;

import com.socialuni.sdk.model.DO.user.SocialUserAccountDO;
import com.socialuni.sdk.utils.CommonUserAccountUtil;
import com.socialuni.sdk.constant.ProviderLoginType;
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
