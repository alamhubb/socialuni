package com.socialuni.social.common.sdk.platform.weixin.login;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class AppLoginVO {
    @NotBlank(message = "参数异常")
    private String openId;
    @NotBlank(message = "参数异常")
    private String access_token;
    private String loginType;
    //qq、wx、phone
    private String provider;
    //mp，app
    private String platform;
    //出生日期
    private String birthday;
    private String avatarUrl;
    @Range(min = 0, max = 2, message = "参数异常")
    private Integer gender;
    private String nickName;
    private String city;
}
