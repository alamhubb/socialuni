package com.socialuni.social.model.model.RO.app;

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
