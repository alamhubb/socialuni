package com.socialuni.social.sdk.model.QO.community.circle;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CircleChatCreateQO {
    @NotBlank
    private String circleName;
    @NotBlank
    private String groupChatId;
}
