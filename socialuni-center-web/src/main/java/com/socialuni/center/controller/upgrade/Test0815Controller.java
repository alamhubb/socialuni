package com.socialuni.center.controller.upgrade;

import com.socialuni.social.api.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class Test0815Controller {
    @Resource
    Test0815Service test0815Service;

    @GetMapping("testUserUid")
    public ResultRO<Void> testUserUid(Integer num) {
        test0815Service.testUserUid(num);
        return ResultRO.success();
    }
}
