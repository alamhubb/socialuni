package com.socialuni.social.sdk.model.RO.message.chat;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-05-26 21:19
 */
@Data
//添加注释
public class ChatReadVO {
    private Integer chatUserId;
    private Integer chatId;
    @NotNull
    private List<Integer> messageIds;
}
