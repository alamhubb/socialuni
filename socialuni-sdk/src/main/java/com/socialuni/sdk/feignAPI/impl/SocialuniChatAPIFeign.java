package com.socialuni.sdk.feignAPI.impl;


import com.socialuni.sdk.feignAPI.SocialuniChatAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@FeignClient(name = "chat", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniChatAPIFeign extends SocialuniChatAPI {
}

