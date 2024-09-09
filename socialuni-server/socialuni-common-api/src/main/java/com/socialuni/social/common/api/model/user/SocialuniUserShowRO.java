package com.socialuni.social.common.api.model.user;

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
public class SocialuniUserShowRO extends SocialuniUserRO {
    //是否为当前用户, 仅在后台使用，
    // 前台不该使用，因为后台返回时如果正确设置需要遍历，麻烦且没有必要。
    //感觉没什么用，看看后端有什么使用场景，观察下，后台不使用则没用
    @Deprecated
    private Boolean isMine;
    private String type;
    //必须为string，返回给app后是uuid无法变为int
    private String city;
    private String birthday;

    public SocialuniUserShowRO(SocialuniUserRO userRO) {
        super(userRO);
    }

    public SocialuniUserShowRO(SocialuniUserShowRO userRO) {
        super(userRO);
        this.isMine = userRO.getIsMine();
        this.city = userRO.getCity();
        this.type = userRO.getType();
        this.birthday = userRO.getBirthday();
    }
}
