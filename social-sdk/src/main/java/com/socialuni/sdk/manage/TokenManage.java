package com.socialuni.sdk.manage;

import com.socialuni.constant.DateTimeType;
import com.socialuni.entity.model.DO.user.TokenDO;
import com.socialuni.sdk.repository.CommonTokenRepository;
import com.socialuni.sdk.utils.CommonTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TokenManage {
    @Resource
    CommonTokenRepository commonTokenRepository;

    public TokenDO create(Integer userId) {
        String token = CommonTokenUtil.generateTokenByUserKey(userId.toString());
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
