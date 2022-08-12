package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniFollowAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "follow", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniFollowAPIFeign  extends SocialuniFollowAPI {


}