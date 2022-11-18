package com.socialuni.social.user.sdk.utils;

import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.user.sdk.model.DO.SocialTokenDO;
import com.socialuni.social.user.sdk.repository.SocialuniCommonTokenRepository;
import com.socialuni.social.common.api.utils.IntegerUtils;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialUserTokenExpireException;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialTokenDOUtil {
    private static SocialuniCommonTokenRepository commonTokenRepository;

    @Resource
    public void setCommonTokenRepository(SocialuniCommonTokenRepository commonTokenRepository) {
        SocialTokenDOUtil.commonTokenRepository = commonTokenRepository;
    }

    public static SocialTokenDO getCommonTokenDOAllowNull() {
        String token = SocialTokenFacade.getToken();
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return SocialTokenDOUtil.getCommonTokenDONotNull(token);
    }

    public static SocialTokenDO getCommonTokenDOAllowNull(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return SocialTokenDOUtil.getCommonTokenDONotNull(token);
    }


    public static SocialTokenDO getCommonTokenDONotNull(String token) {
        String userKey = SocialTokenFacade.getUserKeyByToken(token);
        if (userKey == null) {
            throw new SocialNotLoginException();
        }
        SocialTokenDO tokenDO = commonTokenRepository.findOneByToken(token);
        if (tokenDO == null) {
            throw new SocialNotLoginException();
        }
        Integer doUserId = tokenDO.getUserId();
        Integer userId;
        if (IntegerUtils.strIsAllNumber(userKey)) {
            //解析token
            userId = Integer.valueOf(userKey);
            if (!userId.equals(doUserId)) {
                log.error("绕过验证，错误的userId:{},{}", doUserId, userId);
                RequestLogDO requestLogDO = RequestLogUtil.get();
                requestLogDO.setUserId(doUserId);
                RequestLogUtil.save(requestLogDO);
                throw new SocialNotLoginException();
            }
        } else {
            String uid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(doUserId);

            if (!userKey.equals(uid)) {
                log.error("绕过验证，错误的userId:{},{}", uid, userKey);
                RequestLogDO requestLogDO = RequestLogUtil.get();
                if (requestLogDO != null) {
                    requestLogDO.setUserId(doUserId);
                    RequestLogUtil.save(requestLogDO);
                }
                throw new SocialNotLoginException();
            }
        }
        Date date = new Date();
        //如果当前时间大于时效时间，则时效了
        if (date.getTime() > tokenDO.getExpiredTime().getTime()) {
            //"用户凭证过期，请重新登录",必须用这个
//            return null;
            throw new SocialUserTokenExpireException();
        }
        //返回user
        return tokenDO;
    }
}