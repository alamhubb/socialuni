package com.socialuni.social.common.sdk.model;

import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;
import lombok.Data;

import java.util.List;

@Data
public class SocialAppLaunchDataRO {
    //有无用户都要返回
    private SocialuniAppConfigBO appConfig;
    private SocialuniAppMoreConfigBO appMoreConfig;
    private List<String> reportTypes;
    private Integer onlineUsersCount;
    private String publicKey;
}
