package com.socialuni.api.feignAPI;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("cos")
@FeignClient(name = "cos", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniCosAPI {
    @PostMapping("getCosAuth")
    ResultRO<SocialCosAuthRO> getCosAuth();
}