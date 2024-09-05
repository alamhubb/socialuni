package com.socialuni.social.user.sdk.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author qinkaiyuan
 * @date 2019-10-28 16:11
 */
@Data
public class UserQueryVO {
    @NotNull(message = "入参为空异常")
    private Long userId;
}
