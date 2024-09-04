package com.socialuni.social.community.sdk.model.QO.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SocialTalkIdQO {
    @NotNull(message = "动态标识不能为空")
    private Long talkId;

    public SocialTalkIdQO(@NotNull(message = "动态标识不能为空") Long talkId) {
        this.talkId = talkId;
    }
}