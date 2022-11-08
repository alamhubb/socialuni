package com.socialuni.social.user.sdk.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class SocialUserSchoolNameEditQO {
    @NotBlank(message = "入参为空异常")
    private String schoolName;
}
