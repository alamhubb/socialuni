package com.socialuni.social.community.sdk.model.QO.circle;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CircleChatCreateQO {
    @NotBlank
    private String circleName;
    @NotBlank
    private String groupChatId;
}
