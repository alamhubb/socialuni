package com.socialuni.social.sdk.model.RO.devAccount;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@Data
@NoArgsConstructor
public class DevAccountRO {
    private String appName;
    private String avatarUrl;

    public DevAccountRO(String appName) {
        this.appName = appName;
    }

    public DevAccountRO(String appName, String avatarUrl) {
        this.appName = appName;
        this.avatarUrl = avatarUrl;
    }
}
