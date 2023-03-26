package com.socialuni.social.im.model.QO.friend;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SocialuniFriendAddQO {
    @NotBlank
    private String userId;
    private String applyMsg;
    @NotBlank
    private String reqType;
}
