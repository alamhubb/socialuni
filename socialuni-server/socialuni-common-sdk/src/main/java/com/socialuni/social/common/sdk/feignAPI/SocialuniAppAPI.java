package com.socialuni.social.common.sdk.feignAPI;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.RO.SocialuniTalkTabRO;
import com.socialuni.social.common.sdk.model.SocialAppLaunchDataRO;
import com.socialuni.social.common.sdk.model.SocialuniGetAppInitDataQO;
import com.socialuni.social.common.sdk.model.VO.FrontErrorLogVO;
import com.socialuni.social.common.sdk.model.VO.HomeSwiperVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
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
    ResultRO<List<SocialuniTalkTabRO>> queryHomeTabs();

    @PostMapping("sendErrorLog")
    ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO);


    @PostMapping("getDeviceUid")
    ResultRO<String> getDeviceUid(@RequestBody @NotNull SocialuniGetAppInitDataQO deviceUidQO);
}

