package com.socialuni.sdk.model.RO.talk.circle;

import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class CircleTypeRO {
    public Integer id;
    public String name;
    public Boolean selected;
    private Integer talkCount;
    private Integer count;
    private List<SocialCircleRO> circles;
}
