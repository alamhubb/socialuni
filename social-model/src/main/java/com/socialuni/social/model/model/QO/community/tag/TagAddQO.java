package com.socialuni.social.model.model.QO.community.tag;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class TagAddQO {
    @NotBlank
    public String tagName;
    public String description;
}
