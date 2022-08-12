package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniHugAPI;
import com.socialuni.sdk.model.QO.CenterHugAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@FeignClient(name = "hug", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniHugAPIFeign extends SocialuniHugAPI {
}