package com.socialuni.social.common.api.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SocialuniMineUserRO extends SocialuniUserRO {
    private String phoneNum;

    public SocialuniMineUserRO(SocialuniUserRO userRO) {
        super(userRO);
    }

    public SocialuniMineUserRO(SocialuniMineUserRO userRO) {
        super(userRO);
        this.phoneNum = userRO.getPhoneNum();
    }
}
