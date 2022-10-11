package com.socialuni.social.sdk.feignAPI.openData;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.RO.app.SocialCosAuthRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("cos")
@FeignClient(name = "cos", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/cos")
@Tag(name = "开放数据/腾讯云内容")
public interface SocialuniCosAPI {
    @PostMapping("getCosAuth")
    ResultRO<SocialCosAuthRO> getCosAuth();
}
