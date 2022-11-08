package com.socialuni.social.user.sdk.platform;

import lombok.Data;

@Data
public class WxUserInfo {
    //qq、wx、phone,前台叫provider
    private String openId;
    private String unionId;
}
