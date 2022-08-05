package com.socialuni.center.web.utils;

import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.feignAPI.SocialuniDevAccountAPI;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.dev.DevAccountProviderDO;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.repository.dev.DevAccountProviderRepository;
import com.socialuni.center.web.repository.dev.DevAccountRepository;
import com.socialuni.center.web.repository.dev.DevTokenRepository;
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
    private static SocialuniDevAccountAPI socialuniDevAccountAPI;

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

    @Resource
    public void setSocialuniDevAccountAPI(SocialuniDevAccountAPI socialuniDevAccountAPI) {
        DevAccountUtils.socialuniDevAccountAPI = socialuniDevAccountAPI;
    }

    public static String getDevSocialSecretKey() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        return devAccountDO.getSecretKey();
    }


    public static String getAppSocialuniId() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        return devAccountDO.getSocialuniId();
    }


    public static String getAppGenderType() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO != null) {
            return devAccountDO.getAppGenderType();
        }
        return GenderType.all;
    }

    public static Long getDevNum() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        return devAccountDO.getDevNum();
    }

    /*public static Integer getDevIdAllowNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO != null) {
            return devAccountDO.getId();
        }
        return null;
    }*/

    public static Integer getDevIdNotNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        return devAccountDO.getId();
    }

    public static Integer getDataDevIdNotNull() {
        String dataSocialuniId = RequestUtil.getDataSocialuniId();
        if (StringUtils.isEmpty(dataSocialuniId)) {
            return 1;
        }
        //先从req中获取
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountBySocialuniId(dataSocialuniId);
        if (devAccountDO == null) {
            throw new SocialParamsException("不存在的联盟Id");
        }
        return devAccountDO.getId();
    }

    public static Integer getCenterDevIdNotNull() {
        String socialuniId = SocialAppConfig.getCenterSocialuniId();
        DevAccountDO centerDevAccount = DevAccountUtils.getDevAccountBySocialuniId(socialuniId);
        if (centerDevAccount == null) {
            throw new SocialParamsException("不存在的联盟开发者ID");
        }
        return centerDevAccount.getId();
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
        String secretKey = RequestUtil.getHeader(SocialFeignHeaderName.socialuniSecretKey);
        if (StringUtils.isEmpty(secretKey)) {
            return null;
        }
        /*DevAccountDO devAccountDO;
        if (SocialAppEnv.getContainsProdEnv()) {
            devAccountDO = devAccountRepository.findOneBySecretKey(secretKey);
        } else {
            ResultRO<DevAccountDO> resultRO = socialuniDevAccountAPI.queryDevAccount(new DevAccountQueryQO(secretKey));
            devAccountDO = resultRO.getData();
        }*/
        DevAccountDO devAccountDO = devAccountRepository.findOneBySecretKey(secretKey);
        return devAccountDO;
    }

    public static DevAccountDO getDevAccountNotNull() {
        //先从req中获取
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO == null) {
            devAccountDO = DevAccountUtils.getDevAccount(1);
//            throw new SocialBusinessException("开发者信息为空");
        }
        return devAccountDO;
    }


    public static boolean notPushServer() {
        return !DevAccountUtils.pushServer();
    }

    public static boolean pushServer() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        Integer devId = devAccountDO.getId();
        return devId == 1;
    }

    public static DevAccountDO getAdminDevAccountNotNull() {
        DevAccountDO user = DevAccountUtils.getAdminDevAccountAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        return user;
    }

    public static DevAccountDO getAdminDevAccountAllowNull() {
        String token = SocialTokenUtil.getToken();
        return DevAccountUtils.getDevAccountByToken(token);
    }

    //得到用户信息
    private static DevAccountDO getDevAccountByToken(String token) {
        //开发和生产逻辑不一样，开发从生产拿数据，生产直接从库里拿数据
        /*if (SocialAppEnv.getContainsProdEnv()) {
            //校验解析token
            String devSecretKey = SocialTokenUtil.getUserKeyByToken(token);
            if (StringUtils.isEmpty(devSecretKey)) {
                return null;
            }
            DevAccountDO devAccountDO = devAccountRepository.findOneBySecretKey(devSecretKey);
            if (devAccountDO == null) {
                throw new SocialParamsException("token被破解");
            }
            //todo 这里需要校验有效期吧
            String tokenCode = devTokenRepository.findFirstTokenCodeByUserId(devAccountDO.getId());
            if (!token.equals(tokenCode)) {
                return null;
            }
            return devAccountDO;
        } else {
            //校验解析token
            String devSecretKey = SocialTokenUtil.getUserKeyByToken(token);
            if (StringUtils.isEmpty(devSecretKey)) {
                return null;
            }
            ResultRO<DevAccountDO> resultRO = socialuniDevAccountAPI.queryDevAccount(new DevAccountQueryQO(devSecretKey));

            DevAccountDO devAccountDO = resultRO.getData();
            DevTokenDO devTokenDO = devTokenRepository.findFirstByTokenCode(token);
            if (devAccountDO == null || devTokenDO == null) {
                return null;
            }
            //todo 这里需要校验有效期吧
            if (!devTokenDO.getDevId().equals(devAccountDO.getId())) {
                throw new SocialParamsException("token被破解");
            }
            return devAccountDO;
        }*/
        //校验解析token
        String devSecretKey = SocialTokenUtil.getUserKeyByToken(token);
        if (StringUtils.isEmpty(devSecretKey)) {
            return null;
        }
        DevAccountDO devAccountDO = devAccountRepository.findOneBySecretKey(devSecretKey);
        if (devAccountDO == null) {
            throw new SocialParamsException("token被破解");
        }
        //todo 这里需要校验有效期吧
        String tokenCode = devTokenRepository.findFirstTokenCodeByUserId(devAccountDO.getId());
        if (!token.equals(tokenCode)) {
            return null;
        }
        return devAccountDO;
    }

    public static DevAccountDO getDevAccount(Integer devId) {
        return devAccountRepository.findOneById(devId);
    }

    public static DevAccountDO getDevAccountBySocialuniId(String socialuniId) {
        return devAccountRepository.findOneBySocialuniId(socialuniId);
    }
}
