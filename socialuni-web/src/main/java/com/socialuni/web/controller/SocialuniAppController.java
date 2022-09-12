package com.socialuni.web.controller;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.model.RO.app.HomeSwiperVO;
import com.socialuni.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.sdk.model.RO.app.HomeTabRO;
import com.socialuni.sdk.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.sdk.logic.service.SocialuniAppService;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@RequestMapping("app")
public class SocialuniAppController {
    @Resource
    SocialuniAppService centerAppService;

    @PostMapping("getAppLaunchData")
    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return centerAppService.getAppConfig();
    }

    @PostMapping("queryHomeSwipers")
    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return centerAppService.queryHomeSwipers();
    }


    @PostMapping("queryHomeTabs")
    public ResultRO<List<HomeTabRO>> queryHomeTabs() {
        List<HomeTabRO> list = SocialuniAppConfig.getHomeTabNames().stream().map(HomeTabRO::new).collect(Collectors.toList());
        return ResultRO.success(list);
    }

    @PostMapping("sendErrorLog")
    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return centerAppService.sendErrorLog(frontErrorLogVO);
    }
}
