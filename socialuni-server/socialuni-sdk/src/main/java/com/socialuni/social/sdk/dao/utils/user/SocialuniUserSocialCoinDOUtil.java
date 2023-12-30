package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserCoinDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserCoinRepository;
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


    public static SocialuniUserCoinDo getAllowNull(Integer userId) {
        SocialuniUserCoinDo socialuniUserCoinDo = socialuniUserSocialCoinApi.findByUserId(userId);
        return socialuniUserCoinDo;
    }

    public static SocialuniUserCoinDo getNotNull(Integer userId) {
        SocialuniUserCoinDo socialuniUserCoinDo = getAllowNull(userId);
        if (socialuniUserCoinDo == null) {
            throw new SocialParamsException("未给用户创建金币表数据");
        }
        return socialuniUserCoinDo;
    }

    public static SocialuniUserCoinDo save(SocialuniUserCoinDo socialuniUserExpandDO) {
        return socialuniUserSocialCoinApi.savePut(socialuniUserExpandDO);
    }

    public static SocialuniUserCoinDo getOrCreate(Integer userId) {
        SocialuniUserCoinDo socialuniUserCoinDo = getAllowNull(userId);
        if (socialuniUserCoinDo == null) {
            socialuniUserCoinDo = new SocialuniUserCoinDo();
            socialuniUserCoinDo.setUserId(userId);
            socialuniUserCoinDo.setCoin(0);
            socialuniUserCoinDo = save(socialuniUserCoinDo);
        }
        return socialuniUserCoinDo;
    }
}
