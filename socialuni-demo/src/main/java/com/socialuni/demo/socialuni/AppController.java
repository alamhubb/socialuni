package com.socialuni.demo.socialuni;

import com.socialuni.api.feignAPI.SocialuniAppAPI;
import com.socialuni.demo.service.AppService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.model.model.QO.FrontErrorLogVO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
public class AppController implements SocialuniAppAPI {
    @Resource
    SocialuniAppAPI socialuniAppAPI;
    @Resource
    AppService appService;

    @Override
    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return socialuniAppAPI.getAppLaunchData();
    }

    @Override
    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return appService.queryHomeSwipers();
    }

    @Override
    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return appService.sendErrorLog(frontErrorLogVO);
    }
}
