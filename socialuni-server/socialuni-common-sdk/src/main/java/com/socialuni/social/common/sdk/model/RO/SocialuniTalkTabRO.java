package com.socialuni.social.common.sdk.model.RO;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-05-23 17:21
 */
@Data
public class SocialuniTalkTabRO {
    private String name;
//    private Object circle;
    private String type;


    public SocialuniTalkTabRO(String name) {
        this.name = name;
    }
}
