package com.socialuni.admin.web.controller;

import com.socialuni.social.tance.sdk.api.ConfigApi;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.AppConfigDO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("config")
public class AdminConfigController {
    @Resource
    ConfigApi configApi;

    /**
     * 腾讯云手机验证码相关，手机号登陆和绑定都使用这里发送验证码
     */
    @GetMapping("getAllConfigs")
    public List<AppConfigDO> getAllConfigs() {
        return configApi.findAllByDevIdAndStatusOrderByCreateTimeDesc(DevAccountFacade.getDevIdNotNull(), 1);
    }
}
