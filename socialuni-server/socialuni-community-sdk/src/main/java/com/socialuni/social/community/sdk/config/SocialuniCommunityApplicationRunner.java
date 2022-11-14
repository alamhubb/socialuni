package com.socialuni.social.community.sdk.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.community.sdk.model.SocialDistrictRO;
import com.socialuni.social.community.sdk.redis.DistrictRedis;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import lombok.extern.slf4j.Slf4j;
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
public class SocialuniCommunityApplicationRunner implements ApplicationRunner {
    @Resource
    DistrictRedis districtRedis;
    @Resource
    private DevAccountRedisInterface devAccountRedisInterface;

    @Override
    @Async
    public void run(ApplicationArguments args) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        //如果为null，则为default类型
//        violationKeywordsService.refreshKeywords();
        List<SocialDistrictRO> hotDistricts = districtRedis.getHotDistricts();
        AppData.setHotDistricts(hotDistricts);

        List<SocialDistrictRO> allDistricts = districtRedis.getAllDistricts();
        AppData.setAllDistricts(allDistricts);
        //获取省，不包含子节点
    }
}
