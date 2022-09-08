package com.socialuni.sdk.model.RO.talk.circle;

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
