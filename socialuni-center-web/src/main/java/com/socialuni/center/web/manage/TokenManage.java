package com.socialuni.center.web.manage;

import com.socialuni.social.constant.DateTimeType;
import com.socialuni.center.web.model.DO.user.TokenDO;
import com.socialuni.center.web.repository.CommonTokenRepository;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TokenManage {
    @Resource
    CommonTokenRepository commonTokenRepository;

    public TokenDO create(Integer userId) {
        String token = SocialTokenUtil.generateTokenByUserId(userId);
        TokenDO commonUserTokenDO = this.create(userId, token);
        return commonUserTokenDO;
    }

    public TokenDO create(Integer userId, String token) {
        TokenDO commonUserTokenDO = new TokenDO();
        commonUserTokenDO.setToken(token);
        commonUserTokenDO.setUserId(userId);
        Date curDate = new Date();
        commonUserTokenDO.setExpiredTime(new Date(curDate.getTime() + DateTimeType.quarter));

        commonTokenRepository.save(commonUserTokenDO);
        return commonUserTokenDO;
    }
}
