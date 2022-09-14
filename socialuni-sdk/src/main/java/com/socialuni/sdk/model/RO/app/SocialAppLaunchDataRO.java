package com.socialuni.sdk.model.RO.app;

import com.socialuni.sdk.config.SocialuniAppMoreConfigBO;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SocialAppLaunchDataRO {
    //有无用户都要返回
    private SocialuniAppMoreConfigBO appConfig;
    private List<String> reportTypes;
    private Integer onlineUsersCount;
}
