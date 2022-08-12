package com.socialuni.sdk.feignAPI.impl;


import com.socialuni.sdk.feignAPI.SocialuniCircleAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@FeignClient(name = "circle", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniCircleAPIFeign extends SocialuniCircleAPI {
}

