package com.socialuni.social.im.api.model.QO.message;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MsgDeleteVO {
    @NotNull
    private Integer msgId;
    private String deleteReason;
    private Boolean violation;
}