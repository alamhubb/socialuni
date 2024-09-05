package com.socialuni.social.im.api.model.QO.friend;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SocialuniFriendAddQO {
    @NotBlank
    private Long userId;
    private String applyMsg;
    @NotBlank
    private String reqType;
}
