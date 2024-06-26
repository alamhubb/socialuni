package com.socialuni.social.report.sdk;

import com.socialuni.social.content.logic.service.ViolationKeywordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.Resource;

@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SocialuniContentSecurityScheduledTasks {

    @Resource
    private ViolationKeywordsService violationKeywordsService;

    /**
     * @deprecated <a href="https://cloud.tencent.com/developer/article/1582434">Spring Boot 的 application.properties 中相关的配置说明</a>
     * @return
     */
    @Bean
    @Deprecated
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        return taskScheduler;
    }

    /*@Scheduled(fixedRate = 3600000)
    public void queryFaceValueList() {
//        Integer[] boysFaceValues = userRepository.queryUserFaceValueByBoy();
        *//*Integer[] boysFaceValues = [];
        Arrays.sort(boysFaceValues);
        AppConfigStatic.setBoysFaceValues(boysFaceValues);
        Integer[] girlsFaceValues = userRepository.queryUserFaceValueByGirl();
        Arrays.sort(girlsFaceValues);
        AppConfigStatic.setGirlsFaceValues(girlsFaceValues);
        //更新一下用户的在线状态，上次在线时间超过1小时，且还为在线的标识改为已下线
        Date curDate = new Date();
        Date oneHourBefore = new Date(curDate.getTime() - CommonConst.hour);
//        Integer offLineNum = userRepository.updateUsersOnlineFlag(oneHourBefore);
        Integer offLineNum =0;*//*
        WebsocketServer.subOnlineCount(1);
    }*/

    //20分刷新一次
    @Scheduled(fixedRate = 1200000)
    public void queryIllegalWordDOS() {
        violationKeywordsService.refreshKeywords();
    }
}