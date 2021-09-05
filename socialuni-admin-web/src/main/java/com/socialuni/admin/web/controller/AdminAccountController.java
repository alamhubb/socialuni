package com.socialuni.admin.web.controller;


import com.socialuni.admin.web.service.AdminAccountService;
import com.socialuni.center.sdk.redis.DevAccountRedis;
import com.socialuni.center.sdk.repository.DevAccountProviderRepository;
import com.socialuni.center.sdk.repository.DevAccountRepository;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.sdk.repository.TagRepository;
import com.socialuni.social.utils.UUIDUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class AdminAccountController {
    @Resource
    private DevAccountRedis devAccountRedis;
    @Resource
    private DevAccountRepository devAccountRepository;
    @Resource
    private DevAccountProviderRepository devAccountProviderRepository;
    @Resource
    private TagRepository tagRepository;
    @Resource
    private AdminAccountService adminAccountService;


    @PostMapping("getUser")
    public ResultRO<DevAccountRO> getUser() {
        DevAccountDO user = DevAccountUtils.getAdminDevAccountNotNull();
        DevAccountRO devAccountRO = new DevAccountRO(user);
        //则更新用户手机号
        return new ResultRO<>(devAccountRO);
    }

    @PostMapping("resetSecretKey")
    public ResultRO<String> resetSecretKey() {
        DevAccountDO user = DevAccountUtils.getAdminDevAccountNotNull();
        String secretKey = UUIDUtil.getUUID();
        user.setSecretKey(secretKey);
        devAccountRedis.saveDevAccount(user);
        ResultRO<String> ResultRO = new ResultRO<>();
        ResultRO.setData(secretKey);
        //则更新用户手机号
        return ResultRO;
    }

    @PostMapping("updateDevAccount")
    public ResultRO<DevAccountRO> updateDevAccount(@RequestBody @Valid DevAccountUpdateQO devAccountQO) {
        //则更新用户手机号
        return adminAccountService.updateDevAccount(devAccountQO);
    }
}
