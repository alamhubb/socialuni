package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinDO;
import com.socialuni.social.user.sdk.api.SocialuniUserSocialCoinRepository;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserSocialCoinDOUtil {
    static SocialuniUserSocialCoinRepository socialuniUserSocialCoinRepository;

    @Resource
    public void setSocialuniUserSocialCoinRepository(SocialuniUserSocialCoinRepository socialuniUserSocialCoinRepository) {
        SocialuniUserSocialCoinDOUtil.socialuniUserSocialCoinRepository = socialuniUserSocialCoinRepository;
    }


    public static SocialuniUserSocialCoinDO getAllowNull(Integer userId) {
        SocialuniUserSocialCoinDO socialuniUserExpandDO = socialuniUserSocialCoinRepository.findByUserId(userId);
        return socialuniUserExpandDO;
    }

    public static SocialuniUserSocialCoinDO getNotNull(Integer userId) {
        SocialuniUserSocialCoinDO socialuniUserExpandDO = getAllowNull(userId);
        if (socialuniUserExpandDO == null) {
            throw new SocialParamsException("请设置校园名称");
        }
        return socialuniUserExpandDO;
    }

    public static SocialuniUserSocialCoinDO save(SocialuniUserSocialCoinDO socialuniUserExpandDO) {
        return socialuniUserSocialCoinRepository.save(socialuniUserExpandDO);
    }

    public static SocialuniUserSocialCoinDO getOrCreate(Integer userId) {
        SocialuniUserSocialCoinDO socialuniUserExpandDO = getAllowNull(userId);
        if (socialuniUserExpandDO == null) {
            socialuniUserExpandDO = new SocialuniUserSocialCoinDO();
            socialuniUserExpandDO.setUserId(userId);
            socialuniUserExpandDO.setSocialCoin(0);
            socialuniUserExpandDO = save(socialuniUserExpandDO);
        }
        return socialuniUserExpandDO;
    }
}
