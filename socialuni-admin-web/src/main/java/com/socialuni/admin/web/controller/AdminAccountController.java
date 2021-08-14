package com.socialuni.admin.web.controller;


import com.socialuni.admin.web.repository.DevAccountRepository;
import com.socialuni.admin.web.utils.DevUserUtil;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class AdminAccountController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private DevAccountRepository devAccountRepository;

    @PostMapping("getUser")
    public ResultRO<DevUserRO> getUser() {
        DevAccountDO user = DevUserUtil.getDevAccount();
        DevUserRO devUserRO = new DevUserRO(user);
        //则更新用户手机号
        return new ResultRO<>(devUserRO);
    }

    @PostMapping("resetSecretKey")
    public ResultRO<String> resetSecretKey() {
        DevAccountDO user = DevUserUtil.getDevAccount();
        String secretKey = UUIDUtil.getUUID();
        user.setSecretKey(secretKey);
        devAccountRepository.save(user);
        ResultRO<String> ResultRO = new ResultRO<>();
        ResultRO.setData(secretKey);
        //则更新用户手机号
        return ResultRO;
    }
}
