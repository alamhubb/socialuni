package com.socialuni.social.im.api.model.QO.message;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-05-26 21:19
 */
@Data
public class MessageAddVO {
    //接收者 id
    private String chatId;

    private String receiveId;

    private String type;

    @NotBlank
    private String content;
    //暂时未使用
//    private Integer receiveUserId;
}
