package com.socialuni.social.tance.dev.facade;

import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.tance.dev.api.DevAccountProviderInterface;
import com.socialuni.social.tance.dev.api.DevAccountRedisInterface;
import com.socialuni.social.tance.dev.config.DataSourceContext;
import com.socialuni.social.tance.dev.constant.AdminAppConfigConst;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.enumeration.SocialFeignHeaderName;
import com.socialuni.social.tance.dev.model.DevAccountProviderModler;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
@Slf4j
public class DevAccountFacade {
    //前端传入使用
//    public static final String devAccountKey = "devAccount";
//    public static final String appGenderTypeKey = "appGenderType";

    private static DevAccountInterface devAccountApi;
    public final static Integer systemDevId = 1;
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
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountDo.getSecretKey();
    }


    public static String getAppSocialuniId() {
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountDo.getSocialuniId();
    }


    public static String getAppGenderType() {
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountAllowNull();
        if (devAccountDo != null) {
            return devAccountDo.getAppGenderType();
        }
        return GenderType.all;
    }

    public static Long getDevNum() {
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountDo.getDevNum();
    }

    /*public static Integer getDevIdAllowNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountDO != null) {
            return devAccountDO.getId();
        }
        return null;
    }*/

    public static Integer getDevIdNullElseCenterDevId() {
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountDo.getId();
    }

    public static Integer getAdminDevIdNotNull(Long unionId) {
        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(unionId);

        Integer devId = socialuniUnionIdDo.getFromDevId();
        return devId;
    }

    public static Integer getTestDevIdAllNull() {
        DevAccountDo devAccountDo = devAccountApi.findOneByPhoneNumOrderByIdAsc(SocialuniDevConfig.testUserPhoneNum);
        if (devAccountDo == null) {
            return null;
        }
        return devAccountDo.getId();
    }

    public static String getDevPhoneNumNotNull() {
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountDo.getPhoneNum();
    }

    public static Long getDevUserId() {
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
        return devAccountDo.getUserId();
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
        DevAccountDo centerDevAccount = DevAccountFacade.getDevAccountBySocialuniId(socialuniId);
        if (centerDevAccount == null) {
            throw new SocialParamsException("不存在的联盟开发者ID");
        }
        return centerDevAccount.getId();
    }

    public static Long getSysDevUserId() {
        return DevAccountFacade.getSystemDevAccount().getUserId();
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

    public static String getSocialuniSecretKey() {
        String secretKey = RequestUtil.getRequestValue(SocialFeignHeaderName.socialuniSecretKey);
        log.info("miyaokey:{}", secretKey);
        return secretKey;
    }

    public static DevAccountDo getDevAccountAllowNull() {
        //先从req中获取
        String secretKey = getSocialuniSecretKey();
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
        DevAccountDo devAccountDo = getDevAccountAllowNull();
        if (devAccountDo == null) {
            return null;
        }
        return devAccountDo.getId();
    }


    public static DevAccountDo getDevAccountBySecretKeyNotNull(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            throw new SocialParamsException("开发者信息错误");
        }
        DevAccountDo devAccountDo = devAccountApi.findOneBySecretKey(secretKey);
        if (devAccountDo == null) {
            throw new SocialParamsException("开发者信息错误");
        }
        return devAccountDo;
    }

    public static DevAccountDo getDevAccountAllowNull(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            return getDevAccountNullElseCenterDev();
        }
        return getDevAccountBySecretKeyNotNull(secretKey);
    }

    public static DevAccountDo getDevAccountNotNUll() {
        //先从req中获取
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountAllowNull();
        if (devAccountDo == null) {
            throw new SocialParamsException("开发者信息为空");
        }
        return devAccountDo;
    }

    public static DevAccountDo getDevAccountNullElseCenterDev() {
        //先从req中获取
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountAllowNull();
        if (devAccountDo == null) {
            //每次启动，都用系统默认值，替换insert中的值
//            String phoneNum = SocialuniDevConfig.getSystemUserPhoneNum();
            devAccountDo = getSystemDevAccount();
//            throw new SocialBusinessException("开发者信息为空");
        }
        return devAccountDo;
    }

    public static String getSystemUserPhoneNum() {
        return getSystemDevAccount().getPhoneNum();
    }

    public static DevAccountDo getSystemDevAccount() {
//        String phoneNum = SocialuniDevConfig.getSystemUserPhoneNum();
        DevAccountDo devAccountDo = devAccountApi.findFirstById(DevAccountFacade.systemDevId);
        if (devAccountDo == null) {
            throw new SocialSystemException("开发者信息为空");
        }
        return devAccountDo;
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
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
        Integer devId = devAccountDo.getId();
        return devId == 1;
    }

    public static DevAccountDo getDevAccountByUserId(Long userId) {
        DevAccountDo devAccountDo = devAccountApi.findFirstByUserId(userId);
        return devAccountDo;
    }

    public static DevAccountDo getDevAccount(Integer devId) {
        return devAccountApi.findFirstById(devId);
    }

    //不需要缓存，低频, admin登录使用
    public static DevAccountDo findOneByPhoneNumOrderByIdAsc(String phoneNum) {
        return devAccountApi.findOneByPhoneNumOrderByIdAsc(phoneNum);
    }

    public static DevAccountDo getDevAccountBySocialuniId(String socialuniId) {
        return devAccountApi.findOneBySocialuniId(socialuniId);
    }
}
