package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialAppService;
import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.sdk.url.SocialiuniAppUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
public class SocialAppController implements SocialiuniAppUrl {
    @Resource
    SocialAppService socialAppService;

    @Override
    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return socialAppService.getAppConfig();
    }

    @Override
    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return socialAppService.queryHomeSwipers();
    }
}
