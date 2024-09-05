package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.dao.repository.SocialuniCommonTokenRepository;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.common.api.constant.DateTimeType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SocialuniTokenManage {
    @Resource
    SocialuniCommonTokenRepository commonTokenRepository;

    public SocialuniTokenDO create(Long userId) {
        String token = SocialTokenFacade.generateTokenByUserId(userId);
        SocialuniTokenDO commonUserTokenDO = this.create(userId, token);
        return commonUserTokenDO;
    }

    public SocialuniTokenDO create(Long userId, String token) {
        SocialuniTokenDO commonUserTokenDO = new SocialuniTokenDO();
        commonUserTokenDO.setToken(token);
        commonUserTokenDO.setUserId(userId);
        commonUserTokenDO.setDevId(DevAccountFacade.getDevIdNullElseCenterDevId());
        Date curDate = new Date();
        commonUserTokenDO.setExpiredTime(new Date(curDate.getTime() + DateTimeType.quarter));

        commonTokenRepository.save(commonUserTokenDO);
        return commonUserTokenDO;
    }
}
