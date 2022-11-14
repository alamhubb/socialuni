package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.user.sdk.feignAPI.SocialuniAppNewAPI;
import com.socialuni.social.user.sdk.logic.SocialFrontLogDomain;
import com.socialuni.social.user.sdk.model.FrontErrorLogVO;
import com.socialuni.social.user.sdk.model.SocialAppLaunchDataRO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@RequestMapping("socialuni/socialuniApp")
public class SocialuniAppNewController implements SocialuniAppNewAPI {
    @Resource
    SocialFrontLogDomain socialFrontLogDomain;

    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        SocialAppLaunchDataRO appInitData = new SocialAppLaunchDataRO();
        appInitData.setAppConfig(SocialuniAppConfig.getAppConfig());
        appInitData.setAppMoreConfig(SocialuniAppConfig.getAppMoreConfig());
        return ResultRO.success(appInitData);
    }

    public ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO) {
        return socialFrontLogDomain.sendErrorLog(frontErrorLogVO);
    }
}
