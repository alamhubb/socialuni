package com.socialuni.social.im.model.message.message;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qinkaiyuan
 * @date 2019-05-26 21:19
 */
@Data
public class MessageAddVO {
    @NotNull
    private Integer chatId;

    @NotBlank
    private String content;
    //暂时未使用
    private Integer receiveUserId;
}
