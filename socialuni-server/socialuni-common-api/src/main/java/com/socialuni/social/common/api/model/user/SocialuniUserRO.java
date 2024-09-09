package com.socialuni.social.common.api.model.user;

import com.socialuni.social.common.api.model.SocialuniContentIdRO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserRO extends SocialuniUserConciseRO {
    //是否为当前用户
    private Boolean isMine;
    private String type;
    //必须为string，返回给app后是uuid无法变为int
    private String city;
    private String birthday;

    public SocialuniUserRO(SocialuniUserRO userRO) {
        super(userRO);
        this.isMine = userRO.getIsMine();
        this.city = userRO.getCity();
        this.type = userRO.getType();
        this.birthday = userRO.getBirthday();
    }
}
