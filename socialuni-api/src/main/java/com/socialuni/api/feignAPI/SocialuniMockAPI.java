package com.socialuni.api.feignAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mock")
@FeignClient(name = "mock", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniMockAPI {

//    @PostMapping("mockOAuthUserInfo")
//    ResultRO<SocialLoginRO<CenterUserDetailRO>> mockOAuthUserInfo(@RequestBody MockQO mockQO);
//
//
//    @PostMapping("mockOAuthUserPhoneNum")
//    ResultRO<SocialLoginRO<CenterUserDetailRO>> mockOAuthUserPhoneNum(@RequestBody MockQO mockQO);
}