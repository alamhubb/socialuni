package com.socialuni.sdk.model.RO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialMineUserDetailRO extends SocialUserDetailRO {
    private String phoneNum;
    private String birthday;

    public SocialMineUserDetailRO(SocialUserDetailRO userRO) {
        super(userRO);
    }

    public SocialMineUserDetailRO(SocialMineUserDetailRO userRO) {
        super(userRO);
        this.phoneNum = userRO.getPhoneNum();
        this.birthday = userRO.getBirthday();
    }
}
