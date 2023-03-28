package com.socialuni.social.im.api.feign;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/chat")
@FeignClient(name = "message", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/message")
@Tag(name = "消息模块/会话模块",description = "暂未支持")
public interface SocialuniMessageAPI {
    @PostMapping("sendMsg")
    ResultRO<SocialMessageRO> sendMsg(@RequestBody @Valid MessageAddVO messageAddVO);

}

