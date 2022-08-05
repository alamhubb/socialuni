package com.socialuni.center.web.model.QO.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CenterTalkIdQO {
    @NotNull(message = "动态标识不能为空")
    private String talkId;

    public CenterTalkIdQO(@NotNull(message = "动态标识不能为空") String talkId) {
        this.talkId = talkId;
    }
}