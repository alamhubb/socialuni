package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("cos")
@FeignClient(name = "cos", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniCosAPI {
    @PostMapping("getCosAuth")
    ResultRO<SocialCosAuthRO> getCosAuth();
}