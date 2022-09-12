package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.constant.config.SocialuniAppType;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class SocialuniAppConfigurer {
    //
    private String appType = SocialuniAppType.defaultType;
    //首页的tab栏目
    private String followTabName = "关注";
    private String homeTabName = "首页";
    private String cityTabName = "同城";

    private List<String> homeTabNames = Arrays.asList(this.followTabName, this.homeTabName, this.cityTabName);
}
