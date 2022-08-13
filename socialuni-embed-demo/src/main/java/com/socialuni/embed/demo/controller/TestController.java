package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.UserDO;
import com.socialuni.sdk.serive.CenterTalkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> login(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        UserDO userDO = testUserRepository.save(new UserDO(name));

        Map<String, Object> map = new HashMap<>();
        map.put("user", userDO);
        map.put("token", userDO);
        return map;
    }

    @GetMapping("talks")
    public Object talks() {
        return centerTalkService.queryTalks();
    }
}
