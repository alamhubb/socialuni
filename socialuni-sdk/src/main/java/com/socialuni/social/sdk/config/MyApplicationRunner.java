package com.socialuni.social.sdk.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.sdk.constant.AppData;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.dao.redis.DistrictRedis;
import com.socialuni.social.tance.sdk.api.DevSocialuniIdApi;
import com.socialuni.social.sdk.logic.entity.DevAccountEntity;
import com.socialuni.social.sdk.logic.service.ConfigMapRefreshService;
import com.socialuni.social.sdk.logic.service.ViolationKeywordsService;
import com.socialuni.social.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 11:57
 */
@Component
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {
    @Resource
    private ConfigMapRefreshService configMapRefreshService;
    @Resource
    private ViolationKeywordsService violationKeywordsService;
    @Resource
    DistrictRedis districtRedis;
    @Resource
    DevAccountEntity devAccountEntity;

    @Resource
    DevSocialuniIdApi devSocialuniIdApi;

    @Autowired(required = false)
    private SocialuniAppConfig socialuniAppConfig;

    @Override
    @Async
    public void run(ApplicationArguments args) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        //如果为null，则为default类型
        //初始化默认值
        if (socialuniAppConfig != null) {
            String appType = (String) socialuniAppConfig.getClass().getField("appType").get(null);
            SocialuniAppConfigBO socialuniAppConfigBO = (SocialuniAppConfigBO) socialuniAppConfig.getClass().getField("appConfig").get(null);
            SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = (SocialuniAppMoreConfigBO) socialuniAppConfig.getClass().getField("appMoreConfig").get(null);
            SocialuniAppConfig.resetSocialuniAppConfig(appType, socialuniAppConfigBO, socialuniAppMoreConfigBO);
        }
//        log.info("系统配置表数据：{},{}", JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppConfig()), JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppMoreConfig()));

        DevAccountModel devAccountModel = DevAccountFacade.getDevAccount(1);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountModel == null) {
            if (StringUtils.isEmpty(SocialuniSystemConst.getAppSocialuniId())) {
                devAccountEntity.createDevAccount(null);
            } else {
                devAccountEntity.createDevAccount(null, SocialuniSystemConst.getAppSocialuniId());
            }

            /*DevSocialuniIdDO devSocialuniIdDO = new DevSocialuniIdDO();
            //设置第一个的socialuniId
            devSocialuniIdDO.setSocialuniId(devAccountDO.getSocialuniId());
            devSocialuniIdRepository.save(devSocialuniIdDO);*/
        }

        //创建中心
        if (SocialuniSystemConst.serverIsChild()) {
            DevAccountModel centerDevDO = DevAccountFacade.getDevAccountBySocialuniId(SocialuniSystemConst.getCenterSocialuniId());
            if (centerDevDO == null) {
                devAccountEntity.createDevAccount(null, SocialuniSystemConst.getCenterSocialuniId());
            }
        }

        configMapRefreshService.refreshConfigMap();
//        violationKeywordsService.refreshKeywords();
        List<SocialDistrictRO> hotDistricts = districtRedis.getHotDistricts();
        AppData.setHotDistricts(hotDistricts);

        List<SocialDistrictRO> allDistricts = districtRedis.getAllDistricts();
        AppData.setAllDistricts(allDistricts);
        //获取省，不包含子节点
    }
}
