package com.socialuni.center.web.model.QO.community.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SocialTalkIdQO {
    @NotNull(message = "动态标识不能为空")
    private Integer talkId;

    public SocialTalkIdQO(@NotNull(message = "动态标识不能为空") Integer talkId) {
        this.talkId = talkId;
    }
}