package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.sdk.dao.DO.dev.ThirdUserTokenDO;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserTokenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ThirdUserTokenManage {
    @Resource
    ThirdUserTokenRepository thirdUserTokenRepository;

    public ThirdUserTokenDO create(String thirdUserId, Integer devId, Integer mineUserId) {
        //本系统的，生成userToken，有个清池token对应集美token
        String appToken = SocialTokenFacade.createTokenByUserKey(thirdUserId);
        ThirdUserTokenDO thirdToken = new ThirdUserTokenDO(mineUserId, appToken, devId, thirdUserId);
        thirdToken = thirdUserTokenRepository.save(thirdToken);
        return thirdToken;
    }
    //之前是在应用登录，然后来清池授权绑定。
    //清池是特殊的，直接登录的时候就绑定，首先创建 一个账户，
}
