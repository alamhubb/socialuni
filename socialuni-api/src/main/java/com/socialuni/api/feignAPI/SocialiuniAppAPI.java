package com.socialuni.api.feignAPI;

import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RequestMapping("app")
@FeignClient(name = "app", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialiuniAppAPI {
    @PostMapping("getAppLaunchData")
    ResultRO<SocialAppLaunchDataRO> getAppLaunchData();

    @PostMapping("queryHomeSwipers")
    ResultRO<List<HomeSwiperVO>> queryHomeSwipers();
}
