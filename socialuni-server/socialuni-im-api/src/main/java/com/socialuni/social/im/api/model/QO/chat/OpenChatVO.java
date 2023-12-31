package com.socialuni.social.im.api.model.QO.chat;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data
public class OpenChatVO {
    @NotNull
    private Integer id;

    @NotNull
    private Boolean needPayOpen;

    private String content;
}
