package com.socialuni.social.im.api.feign;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.api.model.QO.SocialuniChatCreateQO;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.QO.chat.ChatReadVO;
import com.socialuni.social.im.api.model.QO.chat.OpenChatVO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/chat")
@FeignClient(name = "chat", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/chat")
@Tag(name = "消息模块/会话模块", description = "暂未支持")
public interface SocialuniChatAPI {
    @PostMapping("readChat")
    ResultRO<?> readChatMessages(@RequestBody @Valid ChatReadVO chatVO);

    @PostMapping("queryHomeChat")
    ResultRO<List<ChatRO>> queryHomeChat();

    @GetMapping("queryChatList")
    ResultRO<List<ChatRO>> queryChatList();

    @PostMapping("queryChat")
    ResultRO<ChatRO> queryChat(@RequestBody @Valid @NotNull SocialuniChatQueryQO socialuniChatQueryQO);

    @PostMapping("openChat")
    ResultRO<List<ChatRO>> openChat(@RequestBody @Valid OpenChatVO chatVO);

    /*@PostMapping("queryChat")
    ResultRO<ChatVO> queryChat(@RequestBody @Valid UserIdVO receiveUserVO);

    //开启对话
    //支付金币开启对话
    @PostMapping("openChat")
    ResultRO<ChatVO> openChat(@RequestBody @Valid OpenChatVO chatVO);*/

    @PostMapping("frontDeleteChat")
    ResultRO<?> frontDeleteChat(@RequestBody @Valid SocialuniChatQueryQO chatVO);

    @PostMapping("removeChat")
    ResultRO<?> removeChat(@RequestBody @Valid SocialuniChatQueryQO chatVO);

    @PostMapping("quitGroupChat")
    ResultRO<?> quitGroupChat(@RequestBody @Valid SocialuniChatQueryQO chatVO);

    @PostMapping("createMineSingleChat")
    ResultRO<?> createMineSingleChat();

    @PostMapping("createMineGroupChat")
    ResultRO<?> createMineGroupChat();

    @PostMapping("joinOrCreateGroupChat")
    ResultRO<ChatRO> joinOrCreateGroupChat(@RequestBody SocialuniChatCreateQO socialuniChatCreateQO);
}

