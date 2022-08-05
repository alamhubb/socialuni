package com.socialuni.center.web.feignAPI;

import com.socialuni.center.web.model.QO.location.LocationQueryQO;
import com.socialuni.center.web.model.QO.location.LocationQueryRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("location")
@FeignClient(name = "location", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniLocationAPI {
    @PostMapping("queryLocation")
    ResultRO<LocationQueryRO> queryLocation(@RequestBody @Valid LocationQueryQO queryQO);
}