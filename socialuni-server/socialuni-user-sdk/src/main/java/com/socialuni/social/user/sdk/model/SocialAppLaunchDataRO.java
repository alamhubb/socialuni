package com.socialuni.social.user.sdk.model;

import com.socialuni.social.tance.sdk.config.SocialuniAppConfigBO;
import com.socialuni.social.tance.sdk.config.SocialuniAppMoreConfigBO;
import lombok.Data;

import java.util.List;

@Data
public class SocialAppLaunchDataRO {
    //有无用户都要返回
    private SocialuniAppConfigBO appConfig;
    private SocialuniAppMoreConfigBO appMoreConfig;
    private List<String> reportTypes;
    private Integer onlineUsersCount;
    private String tempKey;
    private String tempValue;
}
