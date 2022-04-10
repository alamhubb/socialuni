package com.socialuni.social.model.model.RO.user;

import lombok.Data;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2022-04-03 23:34
 */
@Data
public class SocialUserIdentityAuthRO {
    private Integer authScore;
    private String hint;
}
