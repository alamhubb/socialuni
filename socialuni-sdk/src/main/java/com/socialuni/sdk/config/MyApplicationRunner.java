package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.AppData;
import com.socialuni.sdk.logic.entity.DevAccountEntity;
import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.sdk.dao.redis.DistrictRedis;
import com.socialuni.sdk.dao.repository.dev.DevSocialuniIdRepository;
import com.socialuni.sdk.logic.service.ConfigMapRefreshService;
import com.socialuni.sdk.logic.service.ViolationKeywordsService;
import com.socialuni.sdk.utils.DevAccountUtils;
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
    DevSocialuniIdRepository devSocialuniIdRepository;

    @Autowired(required = false)
    SocialuniAppConfig socialuniAppConfig;

    @Override
    @Async
    public void run(ApplicationArguments args) {
        //判断是否已经有注册的开发者，没有的话注册。
        if (socialuniAppConfig == null) {
            System.out.println(1111);
        } else {
            System.out.println(222);
            System.out.println(socialuniAppConfig.follow_name);
        }

        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount(1);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountDO == null) {
            if (StringUtils.isEmpty(SocialAppConfig.getAppSocialuniId())) {
                devAccountEntity.createDevAccount(null);
            } else {
                devAccountEntity.createDevAccount(null, SocialAppConfig.getAppSocialuniId());
            }

            /*DevSocialuniIdDO devSocialuniIdDO = new DevSocialuniIdDO();
            //设置第一个的socialuniId
            devSocialuniIdDO.setSocialuniId(devAccountDO.getSocialuniId());
            devSocialuniIdRepository.save(devSocialuniIdDO);*/
        }

        if (SocialAppConfig.serverIsChild()) {
            DevAccountDO centerDevDO = DevAccountUtils.getDevAccountBySocialuniId(SocialAppConfig.getCenterSocialuniId());
            if (centerDevDO == null) {
                devAccountEntity.createDevAccount(null, SocialAppConfig.getCenterSocialuniId());
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
