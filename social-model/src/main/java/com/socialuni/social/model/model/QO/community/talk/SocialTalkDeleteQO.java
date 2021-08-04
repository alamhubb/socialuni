package com.socialuni.social.model.model.QO.community.talk;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SocialTalkDeleteQO {
    @NotNull(message = "动态标识不能为空")
    private Integer talkId;
}