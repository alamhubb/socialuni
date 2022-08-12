package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniUserIdentityAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "userIdentity", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniUserIdentityAPIFeign extends SocialuniUserIdentityAPI {
}