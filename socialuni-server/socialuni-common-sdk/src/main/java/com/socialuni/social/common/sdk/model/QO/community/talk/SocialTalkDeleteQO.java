package com.socialuni.social.common.sdk.model.QO.community.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SocialTalkDeleteQO {
    @NotNull(message = "动态标识不能为空")
    private Integer talkId;

    public SocialTalkDeleteQO(Integer talkId) {
        this.talkId = talkId;
    }
}