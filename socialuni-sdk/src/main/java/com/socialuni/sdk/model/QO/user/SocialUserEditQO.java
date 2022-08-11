package com.socialuni.sdk.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class SocialUserEditQO {
    @NotBlank(message = "入参为空异常")
    private String nickname;
    @NotBlank(message = "入参为空异常")
    private String gender;
    @NotBlank(message = "入参为空异常")
    private String birthday;
    private String city;
}
