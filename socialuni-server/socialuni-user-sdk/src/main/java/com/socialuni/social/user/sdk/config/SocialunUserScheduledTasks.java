package com.socialuni.social.user.sdk.config;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.content.constant.SocialuniUserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SocialunUserScheduledTasks {

    @Resource
    private SocialuniUserRepository userApi;

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


    //10分刷新一次，用户解封
    @Scheduled(fixedRate = 600000)
    public void updateUserStatus() {
        Date curDate = new Date();
        List<SocialuniUserDo> users = userApi.findCanUnfreezeViolationUser(SocialuniUserStatus.violation, curDate);
        for (SocialuniUserDo user : users) {
            user.setUpdateTime(curDate);
            user.setStatus(SocialuniUserStatus.enable);
            userApi.savePut(user);
        }
        log.info("今日时间{}，解封用户数量：{}", curDate, users.size());
    }

    //24小时执行一次，不对应该每天0点执行
    /*@Scheduled(cron = "0 0 0 * * *")
    public void updateUserVipFlag() {
        Date curDate = new Date();
//        Integer count = userRepository.updateUserVipFlag(curDate);
        Integer count = 0;
    }*/
}