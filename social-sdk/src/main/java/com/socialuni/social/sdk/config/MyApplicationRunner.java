package com.socialuni.social.sdk.config;

import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.model.model.RO.app.SocialDistrictRO;
import com.socialuni.social.sdk.constant.AppData;
import com.socialuni.social.sdk.entity.DevAccountEntity;
import com.socialuni.social.sdk.redis.DistrictRedis;
import com.socialuni.social.sdk.repository.dev.DevSocialuniIdRepository;
import com.socialuni.social.sdk.service.ConfigMapRefreshService;
import com.socialuni.social.sdk.service.ViolationKeywordsService;
import com.socialuni.social.sdk.utils.DevAccountUtils;
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

    @Override
    @Async
    public void run(ApplicationArguments args) {
        //判断是否已经有注册的开发者，没有的话注册。

        DevAccountDO devAccountDO = DevAccountUtils.getDevAccount(1);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountDO == null) {
            devAccountEntity.createDevAccount(null);
            /*DevSocialuniIdDO devSocialuniIdDO = new DevSocialuniIdDO();
            //设置第一个的socialuniId
            devSocialuniIdDO.setSocialuniId(devAccountDO.getSocialuniId());
            devSocialuniIdRepository.save(devSocialuniIdDO);*/
        }

        System.out.println(SocialAppConfig.getCenterSocialuniId());
        if (SocialAppConfig.hasCenterServer()) {
            System.out.println("创建中心的开发者");
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
