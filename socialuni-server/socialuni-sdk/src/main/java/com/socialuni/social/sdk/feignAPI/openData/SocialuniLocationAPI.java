package com.socialuni.social.sdk.feignAPI.openData;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.QO.LocationQueryQO;
import com.socialuni.social.user.sdk.model.RO.LocationQueryRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("location")
@FeignClient(name = "location", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/location")
@Tag(name = "开放数据/腾讯云内容")
public interface SocialuniLocationAPI {
    @PostMapping("queryLocation")
    ResultRO<LocationQueryRO> queryLocation(@RequestBody @Valid LocationQueryQO queryQO);
}
