package com.socialuni.social.im.model;

import lombok.Data;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 15:38
 * @since 1.0
 */
@Data
public class ImTokenModel {
    private Long expireTimeSeconds;
    private Long expiredTime;
    private String token;
    private String userID;
}
