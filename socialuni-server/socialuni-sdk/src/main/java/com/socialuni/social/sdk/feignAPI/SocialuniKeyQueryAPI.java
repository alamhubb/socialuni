package com.socialuni.social.sdk.feignAPI;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.model.QO.SocialuniKeyQueryQO;
import com.socialuni.social.sdk.utils.KeyQueryQO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/app")
@FeignClient(name = "keyQuery", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/keyQuery")
@Tag(name = "应用模块/通用查询", description = "通用查询模块")
public interface SocialuniKeyQueryAPI {
    @PostMapping("query")
    ResultRO<Map<String, Object>> query(@RequestBody @Valid KeyQueryQO<SocialuniKeyQueryQO> keyQueryQO);
}

