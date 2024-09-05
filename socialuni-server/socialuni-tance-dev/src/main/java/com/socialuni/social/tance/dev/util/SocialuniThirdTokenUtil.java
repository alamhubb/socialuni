package com.socialuni.social.tance.dev.util;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.tance.dev.dao.repository.SocialuniThirdTokenRepository;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.dao.DO.SocialuniThirdTokenDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniThirdTokenUtil {
    static SocialuniThirdTokenRepository socialuniThirdTokenRepository;

    @Resource
    public void setSocialuniThirdTokenRepository(SocialuniThirdTokenRepository socialuniThirdTokenRepository) {
        SocialuniThirdTokenUtil.socialuniThirdTokenRepository = socialuniThirdTokenRepository;
    }

    public static SocialuniThirdTokenDO createdThirdToken(Long userId, String token, Integer devId) {
        SocialuniThirdTokenDO socialuniThirdTokenDO = new SocialuniThirdTokenDO();
        socialuniThirdTokenDO.setUserId(userId);
        socialuniThirdTokenDO.setToken(token);
        socialuniThirdTokenDO.setDevId(DevAccountFacade.getCenterDevIdNotNull());

        socialuniThirdTokenRepository.savePut(socialuniThirdTokenDO);
        return socialuniThirdTokenDO;
    }

    //1. 一个接口，用户在本系统登录了，但是没有三方系统的token， 这时候需要查询这个用户在三方系统是否存在
    public static SocialuniThirdTokenDO getThirdUserToken(Long userId) {
        SocialuniThirdTokenDO socialuniThirdTokenDO = socialuniThirdTokenRepository.findFirstByUserId(userId);
        return socialuniThirdTokenDO;
    }
    //如果不存在则注册用户

}
