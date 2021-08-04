package com.socialuni.social.model.model.RO;

import lombok.Data;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class SocialOAuthUserRO {
    private String id;
    private String nickname;
    private String avatar;
    private Integer gender;
    private Integer age;
    private String city;
    private String phoneNum;
}
