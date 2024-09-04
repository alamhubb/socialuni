package com.socialuni.social.tance.sdk.facade;

import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.api.DevAccountProviderInterface;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import com.socialuni.social.tance.sdk.config.SocialuniDevConfig;
import com.socialuni.social.tance.sdk.constant.AdminAppConfigConst;
import com.socialuni.social.tance.sdk.enumeration.SocialFeignHeaderName;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.sdk.model.DevAccountProviderModler;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
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
    private final static Integer systemDevId = 1;
    private static DevAccountRedisInterface devAccountRedisInterface;
    private static DevAccountProviderInterface devAccountProviderApi;

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

    @Autowired
    public void setDevAccountRepository(DevAccountInterface devAccountApi) {
        DevAccountFacade.devAccountApi = devAccountApi;
    }

    @Resource
    public void setDevAccountProviderRepository(DevAccountProviderInterface devAccountProviderApi) {
        DevAccountFacade.devAccountProviderApi = devAccountProviderApi;
    }

    public static String getDevSocialSecretKey() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountModel.getSecretKey();
    }


    public static String getAppSocialuniId() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNullElseCenterDev();
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
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountModel.getDevNum();
    }

    /*public static Integer getDevIdAllowNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO != null) {
            return devAccountDO.getId();
        }
        return null;
    }*/

    public static Integer getDevIdNullElseCenterDevId() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountModel.getId();
    }

    public static Integer getAdminDevIdNotNull(Long unionId) {
        SocialuniUnionIdModler socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(unionId);

        Integer devId = socialuniUnionIdDo.getFromDevId();
        return devId;
    }

    public static Integer getTestDevIdAllNull() {
        DevAccountModel devAccountModel = devAccountApi.findOneByPhoneNumOrderByIdAsc(SocialuniDevConfig.testUserPhoneNum);
        if (devAccountModel == null) {
            return null;
        }
        return devAccountModel.getId();
    }

    public static String getDevPhoneNumNotNull() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountModel.getPhoneNum();
    }

    public static Long getDevUserId() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountModel.getUserId();
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
        return getDevAccountBySecretKeyNotNull(secretKey);
    }

    public static Integer getDevIdAllowNull() {
        DevAccountModel devAccountModel = getDevAccountAllowNull();
        if (devAccountModel == null) {
            return null;
        }
        return devAccountModel.getId();
    }


    public static DevAccountModel getDevAccountBySecretKeyNotNull(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            throw new SocialParamsException("开发者信息错误");
        }
        DevAccountModel devAccountModel = devAccountApi.findOneBySecretKey(secretKey);
        if (devAccountModel == null) {
            throw new SocialParamsException("开发者信息错误");
        }
        return devAccountModel;
    }

    public static DevAccountModel getDevAccountAllowNull(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            return getDevAccountNullElseCenterDev();
        }
        return getDevAccountBySecretKeyNotNull(secretKey);
    }

    public static DevAccountModel getDevAccountNotNUll() {
        //先从req中获取
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountAllowNull();
        if (devAccountModel == null) {
            throw new SocialParamsException("开发者信息为空");
        }
        return devAccountModel;
    }

    public static DevAccountModel getDevAccountNullElseCenterDev() {
        //先从req中获取
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountAllowNull();
        if (devAccountModel == null) {
            //每次启动，都用系统默认值，替换insert中的值
//            String phoneNum = SocialuniDevConfig.getSystemUserPhoneNum();
            devAccountModel = getSystemDevAccount();
//            throw new SocialBusinessException("开发者信息为空");
        }
        return devAccountModel;
    }

    public static String getSystemUserPhoneNum() {
        return getSystemDevAccount().getPhoneNum();
    }

    public static DevAccountModel getSystemDevAccount() {
//        String phoneNum = SocialuniDevConfig.getSystemUserPhoneNum();
        DevAccountModel devAccountModel = devAccountApi.findFirstById(1);
        if (devAccountModel == null) {
            throw new SocialSystemException("开发者信息为空");
        }
        return devAccountModel;
    }

    public static Integer getSystemDevAccountDevId() {
        return DevAccountFacade.getSystemDevAccount().getId();
    }


    //为中心向服务器推送
    public static boolean pusherIsCenterServer() {
        return Objects.equals(DevAccountFacade.getDevIdNullElseCenterDevId(), DevAccountFacade.getCenterDevIdNotNull());
    }

    //为自己向自己推送
    public static boolean pusherIsSelfServer() {
        return DevAccountFacade.getDevIdNullElseCenterDevId() == AdminAppConfigConst.devId;
    }

    //非中心和非自己向中心推送
    public static boolean pusherNotSelfCenter() {
        return !DevAccountFacade.pusherIsSelfServer() && !DevAccountFacade.pusherIsCenterServer();
    }

    public static boolean pushServer() {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNullElseCenterDev();
        Integer devId = devAccountModel.getId();
        return devId == 1;
    }

    public static DevAccountModel getDevAccountByUserId(Long userId) {
        DevAccountModel devAccountModel = devAccountApi.findFirstByUserId(userId);
        return devAccountModel;
    }

    public static DevAccountModel getDevAccount(Integer devId) {
        return devAccountApi.findFirstById(devId);
    }

    //不需要缓存，低频, admin登录使用
    public static DevAccountModel findOneByPhoneNumOrderByIdAsc(String phoneNum) {
        return devAccountApi.findOneByPhoneNumOrderByIdAsc(phoneNum);
    }

    public static DevAccountModel getDevAccountBySocialuniId(String socialuniId) {
        return devAccountApi.findOneBySocialuniId(socialuniId);
    }
}
