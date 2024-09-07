package com.socialuni.social.tance.dev.util;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.utils.NumberUtils;
import com.socialuni.social.tance.dev.dao.repository.SocialuniThirdTokenRepository;
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

    public static SocialuniThirdTokenDO createdThirdTokenOrGet(Long userId, String token, Integer devId, String uuid) {
        SocialuniThirdTokenDO socialuniThirdTokenDO = getThirdUserToken(devId, userId);
        if (socialuniThirdTokenDO == null) {
            socialuniThirdTokenDO = new SocialuniThirdTokenDO();
            socialuniThirdTokenDO.setUserId(userId);
            socialuniThirdTokenDO.setDevId(devId);
            if (NumberUtils.strIsAllNumber(uuid)) {
                socialuniThirdTokenDO.setUserUuid(Long.valueOf(uuid));
            } else {
                throw new SocialParamsException("参数异常134226");
            }
        }
        socialuniThirdTokenDO.setToken(token);
        socialuniThirdTokenRepository.savePut(socialuniThirdTokenDO);
        return socialuniThirdTokenDO;
    }

    //1. 一个接口，用户在本系统登录了，但是没有三方系统的token， 这时候需要查询这个用户在三方系统是否存在
    public static SocialuniThirdTokenDO getThirdUserToken(Integer devId, Long userId) {
        SocialuniThirdTokenDO socialuniThirdTokenDO = socialuniThirdTokenRepository.findFirstByDevIdAndUserId(devId, userId);
        return socialuniThirdTokenDO;
    }
    //如果不存在则注册用户

}
