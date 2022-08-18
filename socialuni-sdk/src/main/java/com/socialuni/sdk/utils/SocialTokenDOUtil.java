package com.socialuni.sdk.utils;

import com.socialuni.sdk.config.SocialTokenUtil;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.repository.CommonTokenRepository;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.exception.SocialUserTokenExpireException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialTokenDOUtil {
    private static CommonTokenRepository commonTokenRepository;

    @Resource
    public void setCommonTokenRepository(CommonTokenRepository commonTokenRepository) {
        SocialTokenDOUtil.commonTokenRepository = commonTokenRepository;
    }

    public static SocialTokenDO getCommonTokenDOAllowNull() {
        String token = SocialTokenUtil.getToken();
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
        String userKey = SocialTokenUtil.getUserKeyByToken(token);
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
                throw new SocialSystemException("绕过验证");
            }
        } else {
            String uid = UnionIdDbUtil.getUidByUnionIdNotNull(doUserId);
            if (!userKey.equals(uid)) {
                log.error("绕过验证，错误的userId:{},{}", uid, userKey);
                throw new SocialSystemException("绕过验证");
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