package com.socialuni.social.community.sdk.api;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.community.sdk.model.HomeSwiperVO;
import com.socialuni.social.community.sdk.model.HomeTabRO;
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
@FeignClient(name = "communityApp", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/communityApp")
@Tag(name = "应用模块/启动模块", description = "暂未支持")
public interface SocialuniCommunityAppAPI {
    @PostMapping("queryHomeSwipers")
    ResultRO<List<HomeSwiperVO>> queryHomeSwipers();

    @PostMapping("queryHomeTabs")
    ResultRO<List<HomeTabRO>> queryHomeTabs();
}

