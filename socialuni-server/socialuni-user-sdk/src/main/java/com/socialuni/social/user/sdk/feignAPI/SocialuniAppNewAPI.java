package com.socialuni.social.user.sdk.feignAPI;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.FrontErrorLogVO;
import com.socialuni.social.user.sdk.model.SocialAppLaunchDataRO;
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
@FeignClient(name = "socialuniApp", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/socialuniApp")
@Tag(name = "应用模块/启动模块", description = "暂未支持")
public interface SocialuniAppNewAPI {

    @PostMapping("getAppLaunchData")
    ResultRO<SocialAppLaunchDataRO> getAppLaunchData();

    @PostMapping("sendErrorLog")
    ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO);
}

