package com.socialuni.api.model.QO.talk;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CenterTalkDeleteQO {
    @NotNull(message = "动态标识不能为空")
    private String talkId;
}