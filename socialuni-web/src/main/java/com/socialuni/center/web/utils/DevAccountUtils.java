package com.socialuni.center.web.utils;

import com.socialuni.api.config.SocialFeignHeaderName;
import com.socialuni.center.web.model.DO.DevAccountDO;
import com.socialuni.center.web.model.DO.DevAccountProviderDO;
import com.socialuni.center.web.repository.DevAccountProviderRepository;
import com.socialuni.center.web.repository.DevAccountRepository;
import com.socialuni.social.constant.GenderType;
import com.socialuni.constant.ConstStatus;
import com.socialuni.social.sdk.exception.SocialBusinessException;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.store.SocialUserPhoneStore;
import com.socialuni.social.sdk.utils.RequestUtils;
import com.socialuni.social.sdk.utils.SocialUserUtil;
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

    public static Integer getDevId() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        if (devAccountDO != null) {
            return devAccountDO.getId();
        }
        return null;
    }

    public static DevAccountProviderDO getDevAccountProviderDO(String mpType) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findByDevIdAndMpTypeAndStatus(devAccountDO.getId(), mpType, ConstStatus.enable);

        return devAccountProviderDO;
    }

    public static UserDO getDevAccountUserDO() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();

        String phoneNum = devAccountDO.getPhoneNum();

        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByPhoneNum(phoneNum);

        if (socialUserPhoneDO == null) {
            throw new SocialBusinessException("默认使用开发者账号绑定的手机号对应的清池账号进行测试，请登录清池注册后测试");
        }
        UserDO userDO = SocialUserUtil.get(socialUserPhoneDO.getUserId());
        return userDO;
    }

    public static DevAccountDO getDevAccount() {
        //先从req中获取
        String secretKey = RequestUtils.getHeader(SocialFeignHeaderName.socialSecretKeyHeaderName);
        return devAccountRepository.findFirstBySecretKey(secretKey);
    }

    public static DevAccountDO getDevAccount(Integer devId) {
        return devAccountRepository.findFirstById(devId);
    }
}
