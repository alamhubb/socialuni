package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.user.sdk.api.SocialuniUserSocialCoinInterface;
import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinModel;
import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserSocialCoinDOUtil {
    static SocialuniUserSocialCoinInterface socialuniUserSocialCoinApi;

    @Resource
    public void setSocialuniUserSocialCoinRepository(SocialuniUserSocialCoinInterface socialuniUserSocialCoinApi) {
        SocialuniUserSocialCoinDOUtil.socialuniUserSocialCoinApi = socialuniUserSocialCoinApi;
    }


    public static SocialuniUserSocialCoinModel getAllowNull(Integer userId) {
        SocialuniUserSocialCoinModel socialuniUserExpandDO = socialuniUserSocialCoinApi.findByUserId(userId);
        return socialuniUserExpandDO;
    }

    public static SocialuniUserSocialCoinModel getNotNull(Integer userId) {
        SocialuniUserSocialCoinModel socialuniUserExpandDO = getAllowNull(userId);
        if (socialuniUserExpandDO == null) {
            throw new SocialParamsException("请设置校园名称");
        }
        return socialuniUserExpandDO;
    }

    public static SocialuniUserSocialCoinModel save(SocialuniUserSocialCoinModel socialuniUserExpandDO) {
        return socialuniUserSocialCoinApi.savePut(socialuniUserExpandDO);
    }

    public static SocialuniUserSocialCoinModel getOrCreate(Integer userId) {
        SocialuniUserSocialCoinModel socialuniUserExpandDO = getAllowNull(userId);
        if (socialuniUserExpandDO == null) {
            socialuniUserExpandDO = new SocialuniUserSocialCoinModel();
            socialuniUserExpandDO.setUserId(userId);
            socialuniUserExpandDO.setSocialCoin(0);
            socialuniUserExpandDO = save(socialuniUserExpandDO);
        }
        return socialuniUserExpandDO;
    }
}
