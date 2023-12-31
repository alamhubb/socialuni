package com.socialuni.social.community.sdk.model;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class SocialCircleRO {
    public Integer id;
    public String name;
    public String avatar;
    public String description;
    public Boolean selected;
    private Integer talkCount;
    private Integer count;
}
