package com.socialuni.sdk.model.VO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class HomeTabRO {
    @NotBlank
    public String tagName;
    public String description;
}
