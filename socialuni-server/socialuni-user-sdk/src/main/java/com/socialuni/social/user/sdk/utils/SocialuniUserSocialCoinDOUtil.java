package com.socialuni.social.user.sdk.utils;

import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import com.socialuni.social.user.sdk.dao.repository.SocialuniUserCoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserSocialCoinDOUtil {
    static SocialuniUserCoinRepository socialuniUserSocialCoinApi;

    @Resource
    public void setSocialuniUserSocialCoinRepository(SocialuniUserCoinRepository socialuniUserSocialCoinApi) {
        SocialuniUserSocialCoinDOUtil.socialuniUserSocialCoinApi = socialuniUserSocialCoinApi;
    }


    public static SocialuniUserCoinDo getAllowNull(Long userId) {
        SocialuniUserCoinDo socialuniUserCoinDo = socialuniUserSocialCoinApi.findByUserId(userId);
        return socialuniUserCoinDo;
    }

    public static SocialuniUserCoinDo getNotNull(Long userId) {
        SocialuniUserCoinDo socialuniUserCoinDo = getAllowNull(userId);
        if (socialuniUserCoinDo == null) {
            throw new SocialParamsException("未给用户创建金币表数据");
        }
        return socialuniUserCoinDo;
    }

    public static SocialuniUserCoinDo create(SocialuniUserCoinDo socialuniUserExpandDO) {
        return socialuniUserSocialCoinApi.savePut(socialuniUserExpandDO);
    }

    public static SocialuniUserCoinDo update(SocialuniUserCoinDo socialuniUserExpandDO) {
        return socialuniUserSocialCoinApi.savePut(socialuniUserExpandDO);
    }

    public static SocialuniUserCoinDo getOrCreate(Long userId) {
        SocialuniUserCoinDo socialuniUserCoinDo = getAllowNull(userId);
        if (socialuniUserCoinDo == null) {
            socialuniUserCoinDo = new SocialuniUserCoinDo();
            socialuniUserCoinDo.setUserId(userId);
            socialuniUserCoinDo.setCoin(0);
            socialuniUserCoinDo = create(socialuniUserCoinDo);
        }
        return socialuniUserCoinDo;
    }
}
