package com.socialuni.social.user.sdk.model;

import lombok.Data;

@Data
public class SocialUserDetailFollowRO {
    private Long userId;
    private Integer fansNum;
    private Integer followNum;
}