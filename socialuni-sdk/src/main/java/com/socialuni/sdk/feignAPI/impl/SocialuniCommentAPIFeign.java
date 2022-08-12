package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniCommentAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "comment", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniCommentAPIFeign extends SocialuniCommentAPI {
}