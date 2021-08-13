package com.socialuni.center.web.utils;

import com.socialuni.center.web.model.DO.ThirdUserTokenDO;
import com.socialuni.center.web.repository.ThirdUserTokenRepository;
import com.socialuni.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.exception.SocialNullUserException;
import com.socialuni.sdk.exception.SocialSystemException;
import com.socialuni.sdk.utils.CommonTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class CenterTokenUtil {
    private static ThirdUserTokenRepository thirdUserTokenRepository;

    @Resource
    public void setThirdUserTokenRepository(ThirdUserTokenRepository thirdUserTokenRepository) {
        CenterTokenUtil.thirdUserTokenRepository = thirdUserTokenRepository;
    }

    public static ThirdUserTokenDO getThirdUserTokenDO() {
        String token = CommonTokenUtil.getToken();
        return CenterTokenUtil.getThirdUserTokenDO(token);
    }

    public static ThirdUserTokenDO getThirdUserTokenDO(String token) {
        //解析token
        String userKey = CommonTokenUtil.getUserKeyByToken(token);
        if (StringUtils.isEmpty(userKey)) {
            return null;
        }
        //解析token
        Integer userId = Integer.valueOf(userKey);
        ThirdUserTokenDO tokenDO = thirdUserTokenRepository.findFirstByToken(token);
        Integer devId = DevAccountUtils.getDevId();

        if (tokenDO == null) {
            throw new SocialNullUserException();
        }

        if (!tokenDO.getDevId().equals(devId)) {
            throw new SocialNullUserException();
        }
        Date date = new Date();
        //如果当前时间大于时效时间，则时效了
        if (date.getTime() > tokenDO.getExpiredTime().getTime()) {
            throw new SocialBusinessException("用户凭证过期，请重新登录");
        }
        //数据库的devId
        Integer doUserId = tokenDO.getUserId();
        if (!userId.equals(doUserId)) {
            log.error("绕过验证，错误的userId:{},{}", doUserId, userId);
            throw new SocialSystemException("绕过了系统验证");
        }
        //返回user
        return tokenDO;
    }
}