package com.socialuni.social.tance.sdk.facade;

import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.api.DevAccountProviderInterface;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import com.socialuni.social.tance.sdk.api.DevTokenInterface;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.tance.sdk.enumeration.SocialFeignHeaderName;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.sdk.model.DevAccountProviderModler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class DevAccountFacade {
    //前端传入使用
//    public static final String devAccountKey = "devAccount";
//    public static final String appGenderTypeKey = "appGenderType";

    private static DevAccountInterface devAccountApi;
    private static DevAccountRedisInterface devAccountRedisInterface;
    private static DevAccountProviderInterface devAccountProviderApi;
    private static DevTokenInterface devTokenApi;

    /*    private static SocialUserPhoneRedis socialUserPhoneRedis;
        private static SocialUserPhoneEntity socialUserPhoneEntity;*/
    @Resource
    public void setDevAccountRedisInterface(DevAccountRedisInterface devAccountRedisInterface) {
        DevAccountFacade.devAccountRedisInterface = devAccountRedisInterface;
    }

/*    @Resource
    public void setSocialUserPhoneRedis(SocialUserPhoneRedis socialUserPhoneRedis) {
        DevAccountFacade.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    @Resource
    public void setSocialUserPhoneEntity(SocialUserPhoneEntity socialUserPhoneEntity) {
        DevAccountFacade.socialUserPhoneEntity = socialUserPhoneEntity;
    }*/

    @Resource
    public void setDevTokenRepository(DevTokenInterface devTokenApi) {
        DevAccountFacade.devTokenApi = devTokenApi;
    }

    @Autowired
    public void setDevAccountRepository(DevAccountInterface devAccountApi) {
        DevAccountFacade.devAccountApi = devAccountApi;
    }

    @Resource
    public void setDevAccountProviderRepository(DevAccountProviderInterface devAccountProviderApi) {
        DevAccountFacade.devAccountProviderApi = devAccountProviderApi;
    }

    public static String getDevSocialSecretKey() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        return devAccountModel.getSecretKey();
    }


    public static String getAppSocialuniId() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        return devAccountModel.getSocialuniId();
    }


    public static String getAppGenderType() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountAllowNull();
        if (devAccountModel != null) {
            return devAccountModel.getAppGenderType();
        }
        return GenderType.all;
    }

    public static Long getDevNum() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        return devAccountModel.getDevNum();
    }

    /*public static Integer getDevIdAllowNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO != null) {
            return devAccountDO.getId();
        }
        return null;
    }*/

    public static Integer getDevIdNotNull() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        if (devAccountModel == null) {
            return 1;
        }
        return devAccountModel.getId();
    }

    public static String getDevPhoneNumNotNull() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        return devAccountModel.getPhoneNum();
    }

    public static Integer getDevUserId() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        return devAccountModel.getUserId();
    }

    public static boolean isCenter() {
        DevAccountModel devAccountModel = DevAccountFacade.getAdminDevAccountNotNull();
        return devAccountModel.getId() == 1;
    }


    /*public static Integer getDataOriginalDevIdNotNull() {
        String dataSocialuniId = RequestUtil.getDataOriginalSocialuniId();
        if (StringUtils.isEmpty(dataSocialuniId)) {
            return 1;
        }
        //先从req中获取
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountBySocialuniId(dataSocialuniId);
        if (devAccountDO == null) {
            throw new SocialParamsException("不存在的联盟Id");
        }
        return devAccountDO.getId();
    }*/

    public static Integer getCenterDevIdNotNull() {
        String socialuniId = SocialuniSystemConst.getCenterSocialuniId();
        if (StringUtils.isEmpty(socialuniId)) {
            return null;
        }
        DevAccountModel centerDevAccount = DevAccountFacade.getDevAccountBySocialuniId(socialuniId);
        if (centerDevAccount == null) {
            throw new SocialParamsException("不存在的联盟开发者ID");
        }
        return centerDevAccount.getId();
    }


    //mock登录时使用
    public static DevAccountProviderModler getDevAccountProviderDOByDevAndMpType(Integer devId, String mpType) {
        DevAccountProviderModler devAccountProviderModler = devAccountProviderApi.findOneByDevIdAndMpType(devId, mpType);
        return devAccountProviderModler;
    }

    public static DevAccountProviderModler getDevAccountProviderDOByAppIdAndMpType(String appId, String mpType) {
        DevAccountProviderModler devAccountProviderModler = devAccountProviderApi.findOneByAppIdAndMpType(appId, mpType);
        return devAccountProviderModler;
    }

    public static DevAccountModel getDevAccountAllowNull() {
        //先从req中获取
        String secretKey = RequestUtil.getRequestValue(SocialFeignHeaderName.socialuniSecretKey);
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
        DevAccountModel devAccountModel = devAccountApi.findOneBySecretKey(secretKey);
        if (devAccountModel == null) {
            throw new SocialParamsException("开发者信息错误");
        }
        return devAccountModel;
    }

    public static DevAccountModel getDevAccountNotNull() {
        //先从req中获取
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountAllowNull();
        if (devAccountModel == null) {
            devAccountModel = DevAccountFacade.getDevAccount(1);
//            throw new SocialBusinessException("开发者信息为空");
        }
        return devAccountModel;
    }


    //为中心向服务器推送
    public static boolean pusherIsCenterServer() {
        return Objects.equals(DevAccountFacade.getDevIdNotNull(), DevAccountFacade.getCenterDevIdNotNull());
    }

    //为自己向自己推送
    public static boolean pusherIsSelfServer() {
        return DevAccountFacade.getDevIdNotNull() == 1;
    }

    //非中心和非自己向中心推送
    public static boolean pusherNotSelfCenter() {
        return !DevAccountFacade.pusherIsSelfServer() && !DevAccountFacade.pusherIsCenterServer();
    }

    public static boolean pushServer() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
        Integer devId = devAccountModel.getId();
        return devId == 1;
    }

    public static DevAccountModel getAdminDevAccountNotNull() {
        DevAccountModel user = DevAccountFacade.getAdminDevAccountAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        return user;
    }

    public static DevAccountModel getAdminDevAccountAllowNull() {
        String token = SocialTokenFacade.getToken();
        return DevAccountFacade.getDevAccountByToken(token);
    }

    public static Integer getAdminDevAccountIdAllowNull() {
        DevAccountModel devAccountModel = getAdminDevAccountAllowNull();
        if (devAccountModel == null) {
            return null;
        }
        return devAccountModel.getId();
    }

    //得到用户信息
    private static DevAccountModel getDevAccountByToken(String token) {
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
        String devSecretKey = SocialTokenFacade.getUserKeyByToken(token);
        if (StringUtils.isEmpty(devSecretKey)) {
            return null;
        }
        DevAccountModel devAccountModel = devAccountApi.findOneBySecretKey(devSecretKey);
        if (devAccountModel == null) {
            throw new SocialParamsException("token被破解");
        }
        //todo 这里需要校验有效期吧
        String tokenCode = devTokenApi.findFirstTokenCodeByUserId(devAccountModel.getId());
        if (!token.equals(tokenCode)) {
            return null;
        }
        return devAccountModel;
    }

    public static DevAccountModel getDevAccount(Integer devId) {
        return devAccountApi.findOneById(devId);
    }

    public static DevAccountModel getDevAccountBySocialuniId(String socialuniId) {
        return devAccountApi.findOneBySocialuniId(socialuniId);
    }
}
