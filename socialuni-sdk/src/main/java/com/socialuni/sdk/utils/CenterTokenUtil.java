package com.socialuni.sdk.utils;

import com.socialuni.sdk.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.sdk.dao.repository.dev.ThirdUserTokenRepository;
import com.socialuni.sdk.constant.socialuni.SocialFeignHeaderName;
import com.socialuni.social.web.sdk.exception.SocialNotLoginException;
import com.socialuni.social.web.sdk.exception.SocialSystemException;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import com.socialuni.sdk.config.SocialTokenUtil;
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


    public static String getDataUserUnionId() {
        String thirdUserId = RequestUtil.getHeader(SocialFeignHeaderName.dataUserUnionId);
        return thirdUserId;
    }

    public static Integer getDataDevId() {
        HttpServletRequest request = RequestUtil.getRequest();
        String dataSocialuniId = request.getHeader(SocialFeignHeaderName.dataOriginalSocialuniId);
        if (SocialTokenUtil.isSuccess(dataSocialuniId)) {
            Integer userId = Integer.parseInt(dataSocialuniId);
            return userId;
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