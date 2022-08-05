package com.socialuni.center.web.config;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.center.web.constant.status.UserStatus;
import com.socialuni.center.web.repository.UserRepository;
import com.socialuni.center.web.service.ViolationKeywordsService;
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
public class ScheduledTasks {

    @Resource
    private ViolationKeywordsService violationKeywordsService;

    @Resource
    private UserRepository userRepository;

    @Bean
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

    //10分刷新一次，用户解封
    @Scheduled(fixedRate = 600000)
    public void updateUserStatus() {
        Date curDate = new Date();
        List<UserDO> users = userRepository.findCanUnfreezeViolationUser(UserStatus.violation, curDate);
        for (UserDO user : users) {
            user.setUpdateTime(curDate);
            user.setStatus(UserStatus.enable);
            userRepository.save(user);
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