package com.socialuni.social.common.sdk.model.QO.circle;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CircleChatCreateQO {
    @NotBlank
    private String circleName;
    @NotBlank
    private String groupChatId;
}
