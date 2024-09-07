package com.socialuni.social.tance.dev.logic.manage;

import com.socialuni.social.tance.dev.dao.DO.AppConfigDO;
import com.socialuni.social.tance.dev.dao.repository.AppConfigRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniAppConfigManage {

    @Resource
    AppConfigRepository appConfigRepository;

    public void updateAppConfig(Integer devId, String configKey, String value) {
        Integer id = appConfigRepository.findIdByDevIdAndConfigKey(devId, configKey);
        if (ObjectUtils.isEmpty(id)) {
            AppConfigDO appConfigDO = new AppConfigDO(devId, configKey, value);
            appConfigDO = appConfigRepository.savePut(appConfigDO);
        } else {
            AppConfigDO appConfigDO = appConfigRepository.findFirstById(id);
            appConfigDO.setValue(value);
            appConfigDO = appConfigRepository.savePut(appConfigDO);
        }
    }

    public void updateAppConfig(Integer devId, String configKey, Boolean value) {
        updateAppConfig(devId, configKey, String.valueOf(value));
    }

    public void updateAppConfig(Integer devId, String configKey, List<?> value) {
        updateAppConfig(devId, configKey, StringUtils.join(value));
    }
    public void updateAppConfig(Integer devId, String configKey, Integer value) {
        updateAppConfig(devId, configKey, String.valueOf(value));
    }
}
