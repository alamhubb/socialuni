package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.UserDO;
import com.socialuni.sdk.serive.CenterTalkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @GetMapping("hello")
    public String test() {
        return "hello world";
    }

    @Resource
    TestUserRepository testUserRepository;
    @Resource
    CenterTalkService centerTalkService;

    @GetMapping("login")
    public UserDO login(String name) {
        UserDO userDO = testUserRepository.save(new UserDO(name));
        return userDO;
    }

    @GetMapping("talks")
    public Object talks() {
        return centerTalkService.queryTalks();
    }
}
