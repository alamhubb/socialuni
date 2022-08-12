package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "user", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniUserAPIFeign extends SocialuniUserAPI {
}