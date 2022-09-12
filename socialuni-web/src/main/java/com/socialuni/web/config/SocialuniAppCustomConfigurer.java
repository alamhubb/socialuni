package com.socialuni.web.config;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.config.SocialuniAppConfigurer;
import com.socialuni.sdk.constant.TalkTabType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class SocialuniAppCustomConfigurer extends SocialuniAppConfigurer {
    //首页的tab栏目
    private String followTabName = "关注";
    private String homeTabName = "大学";
    private String cityTabName = "同城";
    private String selfSchoolTabName = "本校";

    private List<String> homeTabNames = Arrays.asList(this.followTabName, this.homeTabName, this.cityTabName, this.selfSchoolTabName);
}
