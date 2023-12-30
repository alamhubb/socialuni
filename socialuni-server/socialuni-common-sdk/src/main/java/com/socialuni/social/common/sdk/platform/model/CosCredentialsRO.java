package com.socialuni.social.common.sdk.platform.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CosCredentialsRO {
    String tmpSecretId;
    String tmpSecretKey;
    String sessionToken;
}
