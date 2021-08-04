package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.AppData;
import com.socialuni.social.model.model.RO.app.SocialDistrictRO;
import com.socialuni.sdk.redis.DistrictRedis;
import com.socialuni.sdk.service.ConfigMapRefreshService;
import com.socialuni.sdk.service.ViolationKeywordsService;
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

    @Override
    @Async
    public void run(ApplicationArguments args) {
        configMapRefreshService.refreshConfigMap();
        violationKeywordsService.refreshKeywords();
        List<SocialDistrictRO> hotDistricts = districtRedis.getHotDistricts();
        List<SocialDistrictRO> allDistricts = districtRedis.getHotDistricts();
        AppData.setHotDistricts(hotDistricts);
        AppData.setAllDistricts(allDistricts);
        //获取省，不包含子节点
    }
}
