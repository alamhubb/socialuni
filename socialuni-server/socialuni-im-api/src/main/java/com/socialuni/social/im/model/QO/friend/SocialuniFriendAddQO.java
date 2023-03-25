package com.socialuni.social.im.model.QO.friend;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SocialuniFriendAddQO {
    private String userId;
    private String applyMsg;
}
