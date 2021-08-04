package com.socialuni.api.model;

import com.socialuni.social.model.model.RO.user.base.SocialListUserRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterListUserRO extends CenterUserRO {
    //必须为string，返回给app后是uuid无法变为int
    private Integer age;
    private String city;

    public CenterListUserRO(SocialListUserRO userRO) {
        super(userRO);
        this.age = userRO.getAge();
        this.city = userRO.getCity();
    }
}
