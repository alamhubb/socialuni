package com.socialuni.center.web.controller.upgrade0823;

import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@RequestMapping("0823")
public class Center0823Controller {
    @Resource
    TestUnionIdService testUnionIdService;

    @GetMapping("unionid")
    public ResultRO<Void> unionid() {
//        testUnionIdService.unionid();
        return new ResultRO<>();
    }
}
