package com.socialuni.sdk.manage;

import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.repository.CommonTokenRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.social.constant.DateTimeType;
import com.socialuni.sdk.config.SocialTokenUtil;
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
