package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniAppAPI;
import com.socialuni.center.web.serive.CenterAppService;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.social.model.model.QO.FrontErrorLogVO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
public class CenterAppController implements SocialuniAppAPI {
    @Resource
    CenterAppService centerAppService;

    @Override
    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return centerAppService.getAppConfig();
    }

    @Override
    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return centerAppService.queryHomeSwipers();
    }

    @Override
    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return centerAppService.sendErrorLog(frontErrorLogVO);
    }
}
