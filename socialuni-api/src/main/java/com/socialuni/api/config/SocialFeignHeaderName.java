package com.socialuni.api.config;

import lombok.Data;

@Data
public class SocialFeignHeaderName {

    public static String socialSecretKeyHeaderName = "secretKey";
    public static String socialTokenHeaderName = "token";

}
