package com.socialuni.center.web.controller;

import com.socialuni.center.web.model.HomeSwiperVO;
import com.socialuni.center.web.model.QO.FrontErrorLogVO;
import com.socialuni.center.web.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.center.web.serive.CenterAppService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
public class CenterAppController {
    @Resource
    CenterAppService centerAppService;

    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return centerAppService.getAppConfig();
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return centerAppService.queryHomeSwipers();
    }

    //查询首页的tabs列表
    public ResultRO<List<HomeSwiperVO>> queryHomeTabs() {
        return centerAppService.queryHomeSwipers();
    }

    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return centerAppService.sendErrorLog(frontErrorLogVO);
    }
}
