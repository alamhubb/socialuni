package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserSocialCoinDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserSocialCoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserSocialCoinDOUtil {
    static SocialuniUserSocialCoinRepository socialuniUserSocialCoinApi;

    @Resource
    public void setSocialuniUserSocialCoinRepository(SocialuniUserSocialCoinRepository socialuniUserSocialCoinApi) {
        SocialuniUserSocialCoinDOUtil.socialuniUserSocialCoinApi = socialuniUserSocialCoinApi;
    }


    public static SocialuniUserSocialCoinDo getAllowNull(Integer userId) {
        SocialuniUserSocialCoinDo socialuniUserExpandDO = socialuniUserSocialCoinApi.findByUserId(userId);
        return socialuniUserExpandDO;
    }

    public static SocialuniUserSocialCoinDo getNotNull(Integer userId) {
        SocialuniUserSocialCoinDo socialuniUserExpandDO = getAllowNull(userId);
        if (socialuniUserExpandDO == null) {
            throw new SocialParamsException("请设置校园名称");
        }
        return socialuniUserExpandDO;
    }

    public static SocialuniUserSocialCoinDo save(SocialuniUserSocialCoinDo socialuniUserExpandDO) {
        return socialuniUserSocialCoinApi.savePut(socialuniUserExpandDO);
    }

    public static SocialuniUserSocialCoinDo getOrCreate(Integer userId) {
        SocialuniUserSocialCoinDo socialuniUserExpandDO = getAllowNull(userId);
        if (socialuniUserExpandDO == null) {
            socialuniUserExpandDO = new SocialuniUserSocialCoinDo();
            socialuniUserExpandDO.setUserId(userId);
            socialuniUserExpandDO.setSocialCoin(0);
            socialuniUserExpandDO = save(socialuniUserExpandDO);
        }
        return socialuniUserExpandDO;
    }
}
