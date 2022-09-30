package com.socialuni.social.tance.util;

import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.entity.DevAccountProviderDO;
import com.socialuni.social.common.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.social.tance.repository.DevAccountProviderRepository;
import com.socialuni.social.tance.sdk.api.DevAccountApi;
import com.socialuni.social.tance.repository.DevTokenRepository;
import com.socialuni.social.common.utils.RequestUtil;
import com.socialuni.social.tance.sdk.enumeration.SocialFeignHeaderName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class DevAccountUtils {
    //前端传入使用
//    public static final String devAccountKey = "devAccount";
//    public static final String appGenderTypeKey = "appGenderType";

    private static DevAccountApi devAccountApi;
    private static DevAccountProviderRepository devAccountProviderRepository;
    private static DevTokenRepository devTokenRepository;

    @Resource
    public void setDevTokenRepository(DevTokenRepository devTokenRepository) {
        DevAccountUtils.devTokenRepository = devTokenRepository;
    }

    @Autowired
    public void setDevAccountRepository(DevAccountApi devAccountApi) {
        DevAccountUtils.devAccountApi = devAccountApi;
    }

    @Resource
    public void setDevAccountProviderRepository(DevAccountProviderRepository devAccountProviderRepository) {
        DevAccountUtils.devAccountProviderRepository = devAccountProviderRepository;
    }

    public static String getDevSocialSecretKey() {
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountNotNull();
        return devAccountModel.getSecretKey();
    }


    public static String getAppSocialuniId() {
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountNotNull();
        return devAccountModel.getSocialuniId();
    }


    public static String getAppGenderType() {
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountModel != null) {
            return devAccountModel.getAppGenderType();
        }
        return GenderType.all;
    }

    public static Long getDevNum() {
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountNotNull();
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
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountNotNull();
        return devAccountModel.getId();
    }

    public static boolean isCenter() {
        DevAccountModel devAccountModel = DevAccountUtils.getAdminDevAccountNotNull();
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
        DevAccountModel centerDevAccount = DevAccountUtils.getDevAccountBySocialuniId(socialuniId);
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

    public static DevAccountModel getDevAccountAllowNull() {
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
        DevAccountModel devAccountModel = devAccountApi.findOneBySecretKey(secretKey);
        if (devAccountModel == null) {
            throw new SocialParamsException("开发者信息错误");
        }
        return devAccountModel;
    }

    public static DevAccountModel getDevAccountNotNull() {
        //先从req中获取
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountAllowNull();
        if (devAccountModel == null) {
            devAccountModel = DevAccountUtils.getDevAccount(1);
//            throw new SocialBusinessException("开发者信息为空");
        }
        return devAccountModel;
    }


    //为中心向服务器推送
    public static boolean pusherIsCenterServer() {
        return Objects.equals(DevAccountUtils.getDevIdNotNull(), DevAccountUtils.getCenterDevIdNotNull());
    }

    //为自己向自己推送
    public static boolean pusherIsSelfServer() {
        return DevAccountUtils.getDevIdNotNull() == 1;
    }

    //非中心和非自己向中心推送
    public static boolean pusherNotSelfCenter() {
        return !DevAccountUtils.pusherIsSelfServer() && !DevAccountUtils.pusherIsCenterServer();
    }

    public static boolean pushServer() {
        DevAccountModel devAccountModel = DevAccountUtils.getDevAccountNotNull();
        Integer devId = devAccountModel.getId();
        return devId == 1;
    }

    public static DevAccountModel getAdminDevAccountNotNull() {
        DevAccountModel user = DevAccountUtils.getAdminDevAccountAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        return user;
    }

    public static DevAccountModel getAdminDevAccountAllowNull() {
        String token = SocialTokenUtil.getToken();
        return DevAccountUtils.getDevAccountByToken(token);
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
        String devSecretKey = SocialTokenUtil.getUserKeyByToken(token);
        if (StringUtils.isEmpty(devSecretKey)) {
            return null;
        }
        DevAccountModel devAccountModel = devAccountApi.findOneBySecretKey(devSecretKey);
        if (devAccountModel == null) {
            throw new SocialParamsException("token被破解");
        }
        //todo 这里需要校验有效期吧
        String tokenCode = devTokenRepository.findFirstTokenCodeByUserId(devAccountModel.getId());
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
