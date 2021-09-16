package com.socialuni.social.model.model.RO;

import lombok.Data;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class SocialOAuthUserRO {
    private String openId;
    private String unionId;
    private String avatarUrl;
    private String nickName;
    private Integer gender;
    private String year;
    private String city;
    private String phoneNum;
}
