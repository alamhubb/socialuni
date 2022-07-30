package com.socialuni.center.web.utils;

import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.entity.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.social.sdk.repository.dev.ThirdUserTokenRepository;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        String token = SocialTokenUtil.getToken();
        return CenterTokenUtil.getThirdUserTokenDO(token);
    }
    public static String getThirdUserId() {
        HttpServletRequest request = RequestUtil.getRequest();
        String thirdUserId = request.getHeader(SocialFeignHeaderName.socialuniThirdUserId);
        if (SocialTokenUtil.isSuccess(thirdUserId)) {
            return thirdUserId;
        }
//        return SocialTokenUtil.getSocialuniToken();
        return null;
    }

    public static ThirdUserTokenDO getThirdUserTokenDO(String token) {
        //解析token
        String userThirdId = SocialTokenUtil.getUserKeyByToken(token);
        if (StringUtils.isEmpty(userThirdId)) {
            return null;
        }
        //解析token
        ThirdUserTokenDO tokenDO = thirdUserTokenRepository.findFirstByToken(token);
        Integer devId = DevAccountUtils.getDevIdNotNull();

        if (tokenDO == null) {
            throw new SocialNotLoginException();
        }

        if (!tokenDO.getDevId().equals(devId)) {
            log.error("开发者信息错误，请清空token");
            throw new SocialNotLoginException();
        }
        Date date = new Date();
        //如果当前时间大于时效时间，则时效了
        if (date.getTime() > tokenDO.getExpiredTime().getTime()) {
//            throw new SocialBusinessException("用户凭证过期，请重新登录");
            throw new SocialNotLoginException();
        }
        //数据库的devId
        String doUserThirdId = tokenDO.getThirdUserId();
        if (!userThirdId.equals(doUserThirdId)) {
            log.error("绕过验证，错误的userId:{},{}", doUserThirdId, userThirdId);
            throw new SocialSystemException("绕过了系统验证");
        }
        //返回user
        return tokenDO;
    }
}