package com.socialuni.social.im.api.friend;


import com.socialuni.social.common.api.model.ResultRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/chat")
@FeignClient(name = "chat", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/chat")
@Tag(name = "消息模块/会话模块",description = "暂未支持")
public interface SocialuniImChatAPI {
//    @PostMapping("openChat")
//    ResultRO<List<ChatRO>> openChat(@RequestBody @Valid OpenChatVO chatVO);

}

