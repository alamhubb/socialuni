package com.socialuni.demo.controller;

import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.demo.service.PhoneService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 */
@RestController
@RequestMapping("phone")
public class PhoneController {
    @Resource
    PhoneService phoneService;

    @PostMapping("bindSocialuniPhoneNum")
    public ResultRO<MineUserDetailRO> bindSocialuniPhoneNum(@RequestBody @Valid SocialProviderLoginQO loginData) {
        return phoneService.bindSocialuniPhoneNum(loginData);
    }
}