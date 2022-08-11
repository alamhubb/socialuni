package com.socialuni.sdk.model.RO;

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
    //这里不存手机号，因为没有意义，不能使用前台的手机号发给后台
    //    private String phoneNum;
}
