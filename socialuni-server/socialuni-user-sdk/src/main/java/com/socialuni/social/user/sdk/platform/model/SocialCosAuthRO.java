package com.socialuni.social.user.sdk.platform.model;

import lombok.Data;

@Data
public class SocialCosAuthRO {
    CosCredentialsRO credentials;
    String requestId;
    String expiration;
    Long startTime;
    Long expiredTime;

    String uploadImgPath;
    String bucket;
    String region;
}
