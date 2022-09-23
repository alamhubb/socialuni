package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.sdk.dao.DO.user.SocialTokenDO;
import com.socialuni.social.sdk.dao.repository.CommonTokenRepository;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.constant.socialuni.DateTimeType;
import com.socialuni.social.sdk.config.SocialTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TokenManage {
    @Resource
    CommonTokenRepository commonTokenRepository;

    public SocialTokenDO create(Integer userId) {
        String token = SocialTokenUtil.generateTokenByUserId(userId);
        SocialTokenDO commonUserTokenDO = this.create(userId, token);
        return commonUserTokenDO;
    }

    public SocialTokenDO create(Integer userId, String token) {
        SocialTokenDO commonUserTokenDO = new SocialTokenDO();
        commonUserTokenDO.setToken(token);
        commonUserTokenDO.setUserId(userId);
        commonUserTokenDO.setDevId(DevAccountUtils.getDevIdNotNull());
        Date curDate = new Date();
        commonUserTokenDO.setExpiredTime(new Date(curDate.getTime() + DateTimeType.quarter));

        commonTokenRepository.save(commonUserTokenDO);
        return commonUserTokenDO;
    }
}
