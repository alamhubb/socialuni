package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.HomeSwiperVO;
import com.socialuni.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.sdk.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RequestMapping("app")
@FeignClient(name = "app", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniAppAPI {
    @PostMapping("getAppLaunchData")
    ResultRO<SocialAppLaunchDataRO> getAppLaunchData();

    @PostMapping("queryHomeSwipers")
    ResultRO<List<HomeSwiperVO>> queryHomeSwipers();

    @PostMapping("sendErrorLog")
    ResultRO<Void> sendErrorLog(@RequestBody @Valid FrontErrorLogVO frontErrorLogVO);
}
