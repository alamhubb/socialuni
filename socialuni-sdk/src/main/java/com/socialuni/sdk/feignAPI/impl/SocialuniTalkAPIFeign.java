package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "talk", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniTalkAPIFeign extends SocialuniTalkAPI {
}