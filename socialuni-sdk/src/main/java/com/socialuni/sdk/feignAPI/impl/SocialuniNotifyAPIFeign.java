package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniNotifyAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "notify", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniNotifyAPIFeign extends SocialuniNotifyAPI {
}
