package com.socialuni.sdk.model.RO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-10-28 16:11
 */
@Data
public class UserIdVO {
    @NotBlank(message = "入参为空异常")
    private String userId;
}
