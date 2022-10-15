package com.socialuni.social.sdk.logic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.sdk.config.SocialuniAppConfig;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-03-20 20:42
 */
@Service
@Slf4j
public class ConfigMapRefreshService {
//    @Resource
//    private AppConfigRepository appConfigRepository;

    public void refreshConfigMap() throws JsonProcessingException {
        /*List<AppConfigDO> appConfigDOS = appConfigRepository.findAllByStatusOrderByCreateTimeDesc(CommonStatus.enable);
        for (AppConfigDO appConfigDO : appConfigDOS) {
            if (ConfigValueType.stringType.equals(appConfigDO.getValueType())) {
                SocialuniAppConfig.appConfig.get AppConfigConst.appConfigMap.put(appConfigDO.getConfigKey(), appConfigDO.getStringValue());
            } else if (ConfigValueType.number.equals(appConfigDO.getValueType())) {
                SocialuniAppConfig.appConfig AppConfigConst.appConfigMap.put(appConfigDO.getConfigKey(), appConfigDO.getNumberValue());
            } else if (ConfigValueType.booleanType.equals(appConfigDO.getValueType())) {
                SocialuniAppConfig.appConfig AppConfigConst.appConfigMap.put(appConfigDO.getConfigKey(), appConfigDO.getBooleanValue());
            }
        }*/
        Integer talkAdInterval = SocialuniAppConfig.getAppMoreConfig().getTalkShowAdInterval();
        Integer talkShowAdCount = SocialuniAppConfig.getAppMoreConfig().getTalkShowAdCount();
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
        //todo 下版本删除AppConfigConst.appConfigMap 相关内容
        AppConfigConst.appConfigMap.put(AppConfigConst.talkShowAdIndexListKey, showAdList);
        SocialuniAppConfig.getAppMoreConfig().setTalkShowAdIndexList(showAdList);
        log.info("系统配置表数据：{},{}", JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppConfig()), JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppMoreConfig()));
    }
}
