package com.socialuni.sdk.feignAPI;


import com.socialuni.sdk.model.RO.message.chat.ChatRO;
import com.socialuni.sdk.model.RO.message.chat.ChatReadVO;
import com.socialuni.sdk.model.RO.message.chat.ChatRemoveVO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("chat")
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

