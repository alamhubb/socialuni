package com.socialuni.center.web.service;

import com.socialuni.center.web.constant.AppConfigConst;
import com.socialuni.center.web.constant.ConfigValueType;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.entity.model.DO.AppConfigDO;
import com.socialuni.center.web.repository.AppConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-03-20 20:42
 */
@Service
@Slf4j
public class ConfigMapRefreshService {
    @Resource
    private AppConfigRepository appConfigRepository;

    public void refreshConfigMap() {
        List<AppConfigDO> appConfigDOS = appConfigRepository.findAllByStatusOrderByCreateTimeDesc(CommonStatus.enable);
        for (AppConfigDO appConfigDO : appConfigDOS) {
            if (ConfigValueType.stringType.equals(appConfigDO.getValueType())) {
                AppConfigConst.appConfigMap.put(appConfigDO.getConfigKey(), appConfigDO.getStringValue());
            } else if (ConfigValueType.number.equals(appConfigDO.getValueType())) {
                AppConfigConst.appConfigMap.put(appConfigDO.getConfigKey(), appConfigDO.getNumberValue());
            } else if (ConfigValueType.booleanType.equals(appConfigDO.getValueType())) {
                AppConfigConst.appConfigMap.put(appConfigDO.getConfigKey(), appConfigDO.getBooleanValue());
            }
        }
        Integer talkAdInterval = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.talkShowAdIntervalKey);
        Integer talkShowAdCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.talkShowAdCountKey);
        if (talkAdInterval < 6) {
            log.error("广告展示频率不能低于6");
        } else if (talkAdInterval > 30) {
            log.warn("广告展示频率不建议大于30，没有意义");
        }
        List<Integer> showAdList = new ArrayList<>();
        //7, 15, 27, 43, 63, 87, 111, 135, 159, 183
         //8 12 16 20 24 24 24 24 24
        //设置广告间隔
        showAdList.add(talkAdInterval - 1);
        for (int i = 0; i < talkShowAdCount - 1; i++) {
            //每次*2 7 15
            int a = talkAdInterval * (i + 2) - 1;
            if (i < 4) {
                //4 ,+一半的次数，叠加
                a = a + (((i + 1) * i) / 2) * talkAdInterval / 2;
            } else {
                a = a + (4 * (i - 1) - 2) * talkAdInterval / 2;
            }
            showAdList.add(a);
        }
        AppConfigConst.appConfigMap.put(AppConfigConst.talkShowAdIndexListKey, showAdList);
        log.info("系统配置表数据：{}", AppConfigConst.appConfigMap);
    }
}
