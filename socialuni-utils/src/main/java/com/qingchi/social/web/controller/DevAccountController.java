package com.qingchi.social.web.controller;

import com.qingchi.base.common.ResultVO;
import com.qingchi.base.model.account.DevAccountDO;
import com.qingchi.base.modelVO.DevUserAddVO;
import com.qingchi.base.repository.openData.DevAccountRepository;
import com.qingchi.base.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class DevAccountController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private DevAccountRepository devAccountRepository;

    @PostMapping("getUser")
    public ResultVO<DevUserAddVO> getUser(DevAccountDO user) {
        DevUserAddVO devUserAddVO = new DevUserAddVO(user);
        //则更新用户手机号
        return new ResultVO<>(devUserAddVO);
    }

    @PostMapping("resetSecretKey")
    public ResultVO<String> resetSecretKey(DevAccountDO user) {
        String secretKey = UUIDUtil.getUUID();
        user.setSecretKey(secretKey);
        devAccountRepository.save(user);
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setData(secretKey);
        //则更新用户手机号
        return resultVO;
    }
}
