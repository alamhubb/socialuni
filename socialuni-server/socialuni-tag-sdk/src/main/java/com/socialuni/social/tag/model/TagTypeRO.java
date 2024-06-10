package com.socialuni.social.tag.model;

import lombok.Data;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class TagTypeRO {
    public Integer id;
    public String name;
    public Boolean selected;
    private Integer talkCount;
    private Integer count;
    private List<TagRO> tags;
}
