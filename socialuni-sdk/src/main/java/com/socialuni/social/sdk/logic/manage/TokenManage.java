package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.sdk.dao.DO.user.SocialTokenDO;
import com.socialuni.social.sdk.dao.repository.CommonTokenRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.constant.socialuni.DateTimeType;
import com.socialuni.social.user.sdk.facade.SocialTokenFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TokenManage {
    @Resource
    CommonTokenRepository commonTokenRepository;

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
