package com.socialuni.social.tance.dao.redis;

import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;
import com.socialuni.social.tance.model.DO.AppConfigDO;
import com.socialuni.social.tance.repository.AppConfigRepository;
import com.socialuni.social.tance.sdk.constant.AppConfigDOKeyConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class SocialuniAppConfigRedis {
    @Resource
    AppConfigRepository appConfigRepository;

    @Cacheable(value = "getSocialuniAppConfig", key = "#devId")
    public SocialuniAppConfigBO getAppConfig(Integer devId) {
        SocialuniAppConfigBO socialuniAppConfigBO = new SocialuniAppConfigBO();

        List<AppConfigDO> configDOS = appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(devId, 1);

        for (AppConfigDO configDO : configDOS) {
            String configKey = configDO.getConfigKey();
            String configValue = configDO.getValue();
            if (StringUtils.isNotEmpty(configKey) && StringUtils.isNotEmpty(configValue)) {
                if (configKey.equals(AppConfigDOKeyConst.appGender)) {
                    socialuniAppConfigBO.setAppGender(configValue);
                } else if (configKey.equals(AppConfigDOKeyConst.followTabName)) {
                    socialuniAppConfigBO.setFollowTabName(configValue);
                } else if (configKey.equals(AppConfigDOKeyConst.homeTabName)) {
                    socialuniAppConfigBO.setHomeTabName(configValue);
                } else if (configKey.equals(AppConfigDOKeyConst.cityTabName)) {
                    socialuniAppConfigBO.setCityTabName(configValue);
                } else if (configKey.equals(AppConfigDOKeyConst.tabNames)) {
                    socialuniAppConfigBO.setTabNames(Arrays.asList(configValue.split(",")));
                } else if (configKey.equals(AppConfigDOKeyConst.defaultChatGroups)) {
                    socialuniAppConfigBO.setDefaultChatGroups(Arrays.asList(configValue.split(",")));
                } else if (configKey.equals(AppConfigDOKeyConst.disableUnderageContent)) {
                    socialuniAppConfigBO.setDisableUnderageContent(Boolean.valueOf(configValue));
                } else if (configKey.equals(AppConfigDOKeyConst.disableContentHasQrCode)) {
                    socialuniAppConfigBO.setDisableContentHasQrCode(Boolean.valueOf(configValue));
                } else if (configKey.equals(AppConfigDOKeyConst.disableContentHasContactInfo)) {
                    socialuniAppConfigBO.setDisableContentHasContactInfo(Boolean.valueOf(configValue));
                } else if (configKey.equals(AppConfigDOKeyConst.mustSetSchoolCanPost)) {
                    socialuniAppConfigBO.setMustSetSchoolCanPost(Boolean.valueOf(configValue));
                }
            }
        }
        return socialuniAppConfigBO;
    }

    @Cacheable(value = "getSocialuniAppMoreConfig", key = "#devId")
    public SocialuniAppMoreConfigBO getAppConfigMore(Integer devId) {

        //未使用数据库的，先这样，到时候再改参考上面
        SocialuniAppMoreConfigBO socialuniAppConfigBO = new SocialuniAppMoreConfigBO();

        return socialuniAppConfigBO;
    }
}
