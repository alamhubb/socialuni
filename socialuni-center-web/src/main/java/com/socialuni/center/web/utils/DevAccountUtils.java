package com.socialuni.center.web.utils;

import com.socialuni.api.config.SocialFeignHeaderName;
import com.socialuni.center.web.repository.DevAccountProviderRepository;
import com.socialuni.center.web.repository.DevAccountRepository;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.entity.model.DevAccountProviderDO;
import com.socialuni.social.constant.ConstStatus;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.sdk.store.SocialUserPhoneStore;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DevAccountUtils {
    //前端传入使用
    public static final String devAccountKey = "devAccount";
//    public static final String appGenderTypeKey = "appGenderType";

    private static DevAccountRepository devAccountRepository;
    private static SocialUserPhoneStore socialUserPhoneStore;
    private static DevAccountProviderRepository devAccountProviderRepository;

    @Resource
    public void setDevAccountRepository(DevAccountRepository devAccountRepository) {
        DevAccountUtils.devAccountRepository = devAccountRepository;
    }

    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneStore socialUserPhoneStore) {
        DevAccountUtils.socialUserPhoneStore = socialUserPhoneStore;
    }

    @Resource
    public void setDevAccountProviderRepository(DevAccountProviderRepository devAccountProviderRepository) {
        DevAccountUtils.devAccountProviderRepository = devAccountProviderRepository;
    }

    public static String getDevSocialSecretKey() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        if (devAccountDO != null) {
            return devAccountDO.getSecretKey();
        }
        return null;
    }


    public static String getAppGenderType() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        if (devAccountDO != null) {
            return devAccountDO.getAppGenderType();
        }
        return GenderType.all;
    }

    public static Long getDevNum() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        if (devAccountDO != null) {
            return devAccountDO.getDevNum();
        }
        return null;
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

    public static DevAccountProviderDO getDevAccountProviderDO(String mpType) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findByDevIdAndMpTypeAndStatus(devAccountDO.getId(), mpType, ConstStatus.enable);

        return devAccountProviderDO;
    }

    public static DevAccountDO getDevAccountAllowNull() {
        //先从req中获取
        String secretKey = RequestUtil.getHeader(SocialFeignHeaderName.socialSecretKeyHeaderName);
        if (StringUtils.isEmpty(secretKey)){
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

    public static DevAccountDO getDevAccount(Integer devId) {
        return devAccountRepository.findFirstById(devId);
    }
}
