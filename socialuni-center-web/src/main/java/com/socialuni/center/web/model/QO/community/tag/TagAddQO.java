package com.socialuni.center.web.model.QO.community.tag;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class TagAddQO {
    @NotBlank
    @Length(min = 2, max = 6)
    public String tagName;
    public String description;
}
