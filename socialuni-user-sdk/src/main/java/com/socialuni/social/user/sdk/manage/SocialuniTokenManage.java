package com.socialuni.social.user.sdk.manage;

import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.user.sdk.model.DO.SocialTokenDO;
import com.socialuni.social.user.sdk.repository.SocialuniCommonTokenRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.common.api.constant.DateTimeType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SocialuniTokenManage {
    @Resource
    SocialuniCommonTokenRepository commonTokenRepository;

    public SocialTokenDO create(Integer userId) {
        String token = SocialTokenFacade.generateTokenByUserId(userId);
        SocialTokenDO commonUserTokenDO = this.create(userId, token);
        return commonUserTokenDO;
    }

    public SocialTokenDO create(Integer userId, String token) {
        SocialTokenDO commonUserTokenDO = new SocialTokenDO();
        commonUserTokenDO.setToken(token);
        commonUserTokenDO.setUserId(userId);
        commonUserTokenDO.setDevId(DevAccountFacade.getDevIdNotNull());
        Date curDate = new Date();
        commonUserTokenDO.setExpiredTime(new Date(curDate.getTime() + DateTimeType.quarter));

        commonTokenRepository.save(commonUserTokenDO);
        return commonUserTokenDO;
    }
}
