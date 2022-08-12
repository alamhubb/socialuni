package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniTagAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "tag", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniTagAPIFeign extends SocialuniTagAPI {
}