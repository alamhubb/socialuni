package com.socialuni.social.sdk.url;

import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.social.model.model.QO.FrontErrorLogVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RequestMapping("app")
public interface SocialuniAppUrl {
    @PostMapping("getAppLaunchData")
    ResultRO<SocialAppLaunchDataRO> getAppLaunchData();

    @PostMapping("queryHomeSwipers")
    ResultRO<List<HomeSwiperVO>> queryHomeSwipers();

    @PostMapping("sendErrorLog")
    ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO);
}
