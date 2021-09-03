package com.socialuni.center.sdk.utils;

import com.socialuni.api.config.SocialFeignHeaderName;
import com.socialuni.center.sdk.repository.DevAccountProviderRepository;
import com.socialuni.center.sdk.repository.DevAccountRepository;
import com.socialuni.center.sdk.repository.DevTokenRepository;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.entity.model.DevAccountProviderDO;
import com.socialuni.social.constant.ConstStatus;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DevAccountUtils {
    //前端传入使用
//    public static final String devAccountKey = "devAccount";
//    public static final String appGenderTypeKey = "appGenderType";

    private static DevAccountRepository devAccountRepository;
    private static DevAccountProviderRepository devAccountProviderRepository;
    private static DevTokenRepository devTokenRepository;

    @Resource
    public void setDevTokenRepository(DevTokenRepository devTokenRepository) {
        DevAccountUtils.devTokenRepository = devTokenRepository;
    }

    @Resource
    public void setDevAccountRepository(DevAccountRepository devAccountRepository) {
        DevAccountUtils.devAccountRepository = devAccountRepository;
    }

    @Resource
    public void setDevAccountProviderRepository(DevAccountProviderRepository devAccountProviderRepository) {
        DevAccountUtils.devAccountProviderRepository = devAccountProviderRepository;
    }

    public static String getDevSocialSecretKey() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        return devAccountDO.getSecretKey();
    }


    public static String getAppGenderType() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO != null) {
            return devAccountDO.getAppGenderType();
        }
        return GenderType.all;
    }

    public static Long getDevNum() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        return devAccountDO.getDevNum();
    }

    public static Integer getDevIdAllowNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO != null) {
            return devAccountDO.getId();
        }
        return null;
    }

    public static Integer getDevId() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        return devAccountDO.getId();
    }

    //mock登录时使用
    public static DevAccountProviderDO getDevAccountProviderDOByDevAndMpType(Integer devId, String mpType) {
        DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findOneByDevIdAndMpType(devId, mpType);
        return devAccountProviderDO;
    }

    public static DevAccountProviderDO getDevAccountProviderDOByAppIdAndMpType(String appId, String mpType) {
        DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findOneByAppIdAndMpType(appId, mpType);
        return devAccountProviderDO;
    }

    public static DevAccountDO getDevAccountAllowNull() {
        //先从req中获取
        String secretKey = RequestUtil.getHeader(SocialFeignHeaderName.socialSecretKeyHeaderName);
        if (StringUtils.isEmpty(secretKey)) {
            return null;
        }
        return devAccountRepository.findFirstBySecretKey(secretKey);
    }

    public static DevAccountDO getDevAccount() {
        //先从req中获取
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO == null) {
            throw new SocialBusinessException("开发者信息为空");
        }
        return devAccountDO;
    }

    public static DevAccountDO getAdminDevAccountNotNull() {
        String token = SocialTokenUtil.getToken();
        return DevAccountUtils.getDevAccountByToken(token);
    }

    //得到用户信息
    private static DevAccountDO getDevAccountByToken(String token) {
        //校验解析token
        String userId = SocialTokenUtil.getUserKeyByToken(token);
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        Integer userIdInt = Integer.parseInt(userId);
        //todo 这里需要校验有效期吧
        String tokenCode = devTokenRepository.findFirstTokenCodeByUserId(userIdInt);
        if (!token.equals(tokenCode)) {
            return null;
        }
        DevAccountDO devAccountDO = devAccountRepository.findFirstById(userIdInt);
        if (devAccountDO == null) {
            throw new SocialParamsException("token被破解");
        }
        return devAccountDO;
    }

    /*public static DevAccountDO getDevAccount(Integer devId) {
        return devAccountRepository.findFirstById(devId);
    }*/
}
