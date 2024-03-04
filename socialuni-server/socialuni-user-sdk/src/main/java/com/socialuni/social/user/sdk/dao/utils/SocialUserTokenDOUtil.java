/*
package com.socialuni.sdk.utils.model.DO;

import com.socialuni.user.model.DO.UniUserTokenDO;
import com.socialuni.user.sdk.repository.SocialUserTokenRepository;
import com.socialuni.sdk.utils.SocialUserTokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserTokenDOUtil {
    @Resource
    SocialUserTokenRepository socialUserTokenRepository;

    public UniUserTokenDO create(Integer userId) {
        //本系统的，生成userToken，有个清池token对应集美token
        String appToken = SocialUserTokenUtils.generateTokenById(userId);
        UniUserTokenDO socialUserTokenDO = new UniUserTokenDO(userId, appToken);
        socialUserTokenDO = socialUserTokenRepository.save(socialUserTokenDO);
        return socialUserTokenDO;
    }
}
*/
