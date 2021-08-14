package com.socialuni.center.web.manage;

import com.socialuni.center.web.model.DO.ThirdUserTokenDO;
import com.socialuni.center.web.repository.ThirdUserTokenRepository;
import social.web.sdk.utils.SocialTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ThirdUserTokenManage {
    @Resource
    ThirdUserTokenRepository thirdUserTokenRepository;

    public ThirdUserTokenDO create(Integer userId, Integer devId) {
        //本系统的，生成userToken，有个清池token对应集美token
        String appToken = SocialTokenUtil.generateTokenByUserId(userId);
        ThirdUserTokenDO thirdToken = new ThirdUserTokenDO(userId, appToken, devId);
        thirdToken = thirdUserTokenRepository.save(thirdToken);
        return thirdToken;
    }
    //之前是在应用登录，然后来清池授权绑定。
    //清池是特殊的，直接登录的时候就绑定，首先创建 一个账户，
}
