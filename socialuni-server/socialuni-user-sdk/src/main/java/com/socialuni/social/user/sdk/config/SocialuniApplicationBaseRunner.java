package com.socialuni.social.user.sdk.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.tance.config.ConfigMapRefreshService;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 11:57
 */
@Component
@Slf4j
public class SocialuniApplicationBaseRunner implements ApplicationRunner {
    @Resource
    private ConfigMapRefreshService configMapRefreshService;

    @Resource
    private DevAccountRedisInterface devAccountRedisInterface;

    @Override
    @Async
    public void run(ApplicationArguments args) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        configMapRefreshService.refreshConfigMap();
        //获取省，不包含子节点
    }
}