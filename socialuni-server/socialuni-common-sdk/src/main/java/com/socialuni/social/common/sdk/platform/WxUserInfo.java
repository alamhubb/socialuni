package com.socialuni.social.common.sdk.platform;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class WxUserInfo {
    //qq、wx、phone,前台叫provider
    private String openId;
    private String unionId;
}
