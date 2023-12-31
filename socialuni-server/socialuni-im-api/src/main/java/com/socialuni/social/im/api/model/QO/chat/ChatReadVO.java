package com.socialuni.social.im.api.model.QO.chat;

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
    @NotNull
    private String chatId;
    private List<Integer> messageIds;
}
