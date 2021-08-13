package com.socialuni.center.web.model.RO;

import lombok.Data;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class UserOAuthRO {
    private String unionId;
    private String nickname;
    private String avatar;
    private String gender;
    private Integer age;
    private String city;
}
