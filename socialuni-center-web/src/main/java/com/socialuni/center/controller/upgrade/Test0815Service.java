package com.socialuni.center.controller.upgrade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class Test0815Service {
    @Resource
    Test0815Repository test0815Repository;

    @Async
    public void testUserUid(Integer num) {
        log.info("开始");
        for (int i = 0; i < num; i++) {
            test0815Repository.testUserUid();
            if (i % 1000 == 0) {
                log.info("成功次数{}", i);
            }
        }
    }
}
