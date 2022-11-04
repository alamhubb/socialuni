package com.socialuni.social.sdk.feignAPI;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.social.sdk.model.RO.app.HomeSwiperVO;
import com.socialuni.social.sdk.model.RO.app.HomeTabRO;
import com.socialuni.social.sdk.model.RO.app.SocialAppLaunchDataRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/app")
@FeignClient(name = "app", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/app")
@Tag(name = "应用模块/启动模块", description = "暂未支持")
public interface SocialuniAppAPI {

    @PostMapping("getAppLaunchData")
    ResultRO<SocialAppLaunchDataRO> getAppLaunchData();

    @PostMapping("queryHomeSwipers")
    ResultRO<List<HomeSwiperVO>> queryHomeSwipers();

    @PostMapping("queryHomeTabs")
    ResultRO<List<HomeTabRO>> queryHomeTabs();

    @PostMapping("sendErrorLog")
    ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO);
}

