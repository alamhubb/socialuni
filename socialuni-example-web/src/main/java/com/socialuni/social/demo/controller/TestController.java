package com.socialuni.social.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /**
     * 测试的请求。
     * @return str 测试内容
     */
    @RequestMapping("hello")
    public String hello(){
        return "st";
    }
}
