package com.socialuni.social.im.model.QO.friend;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SocialuniFriendAddQO {
    @NotEmpty
    private String userId;
    private String applyMsg;
}
