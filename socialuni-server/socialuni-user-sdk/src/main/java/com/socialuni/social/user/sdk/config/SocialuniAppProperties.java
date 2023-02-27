package com.socialuni.social.user.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.app")
public class SocialuniAppProperties {
    private String staticResourceUrl;




    /*private String staticResourceUrl;
    private Integer systemUserId;

    //核心圈子，不写默认为app绑定的圈子
    private String mainCircle;
    //辅助圈子，不写默认热门圈子排名
    private String subCircles;

    //首页黑名单的tags
    private String blackTags;*/


}
