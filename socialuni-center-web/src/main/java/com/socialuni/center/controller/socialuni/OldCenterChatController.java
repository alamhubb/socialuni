package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.model.RO.message.chat.ChatRO;
import com.socialuni.sdk.model.RO.message.chat.ChatReadVO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("chat")
public class OldCenterChatController {
    @PostMapping("readChat")
    public ResultRO<?> readChatMessages(@RequestBody @Valid ChatReadVO chatVO) {
        return null;
    }

    @PostMapping("queryHomeChat")
    public ResultRO<List<ChatRO>> queryHomeChat() {
        return null;
    }

    @PostMapping("queryChats")
    public ResultRO<List<ChatRO>> queryChats() {
        List<ChatRO> list = new ArrayList<>();
        return ResultRO.success(list);
    }
}
