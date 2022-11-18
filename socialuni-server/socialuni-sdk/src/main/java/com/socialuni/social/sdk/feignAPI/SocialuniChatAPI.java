package com.socialuni.social.sdk.feignAPI;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.model.RO.message.chat.ChatRO;
import com.socialuni.social.sdk.model.RO.message.chat.ChatReadVO;
import com.socialuni.social.sdk.model.RO.message.chat.ChatRemoveVO;
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
public interface SocialuniChatAPI {
    @PostMapping("readChat")
    ResultRO<?> readChatMessages(@RequestBody @Valid ChatReadVO chatVO);

    @PostMapping("queryHomeChat")
    ResultRO<List<ChatRO>> queryHomeChat();

    @PostMapping("queryChats")
    ResultRO<List<ChatRO>> queryChats();

    /*@PostMapping("queryChat")
    ResultRO<ChatVO> queryChat(@RequestBody @Valid UserIdVO receiveUserVO);

    //开启对话
    //支付贝壳开启对话
    @PostMapping("openChat")
    ResultRO<ChatVO> openChat(@RequestBody @Valid OpenChatVO chatVO);*/

    @PostMapping("frontDeleteChat")
    ResultRO<?> frontDeleteChat(@RequestBody @Valid ChatRemoveVO chatVO);

    @PostMapping("removeChat")
    ResultRO<?> removeChat(@RequestBody @Valid ChatRemoveVO chatVO);

    @PostMapping("closeChat")
    ResultRO<?> closeChat(@RequestBody @Valid ChatRemoveVO chatVO);
}
