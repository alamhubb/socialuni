package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.SocialuniAppAPI;
import com.socialuni.social.sdk.logic.service.SocialuniAppService;
import com.socialuni.social.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.social.sdk.model.RO.app.HomeSwiperVO;
import com.socialuni.social.sdk.model.RO.app.HomeTabRO;
import com.socialuni.social.sdk.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
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
@RequestMapping("socialuni/app")
public class SocialuniAppController implements SocialuniAppAPI {
    @Resource
    SocialuniAppService centerAppService;

    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return centerAppService.getAppConfig();
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return centerAppService.queryHomeSwipers();
    }


    public ResultRO<List<HomeTabRO>> queryHomeTabs() {
        List<HomeTabRO> list = SocialuniAppConfig.getAppConfig().getTabNames().stream().map(HomeTabRO::new).collect(Collectors.toList());
        return ResultRO.success(list);
    }

    public ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO) {
        return centerAppService.sendErrorLog(frontErrorLogVO);
    }
}
