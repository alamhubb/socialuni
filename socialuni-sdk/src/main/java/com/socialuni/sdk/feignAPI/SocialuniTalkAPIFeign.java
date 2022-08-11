package com.socialuni.sdk.feignAPI;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "talk", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniTalkAPIFeign extends SocialuniTalkAPI {
}