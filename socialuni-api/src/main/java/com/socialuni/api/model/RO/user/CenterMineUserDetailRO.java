package com.socialuni.api.model.RO.user;

import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterMineUserDetailRO extends CenterUserDetailRO {
    private String phoneNum;
    private String birthday;

    public CenterMineUserDetailRO(CenterMineUserDetailRO userRO) {
        super(userRO);
        this.phoneNum = userRO.getPhoneNum();
        this.birthday = userRO.getBirthday();
    }

    public CenterMineUserDetailRO(CenterUserDetailRO centerUserDetailRO, SocialMineUserDetailRO userRO) {
        super(centerUserDetailRO);
        this.phoneNum = userRO.getPhoneNum();
        this.birthday = userRO.getBirthday();
    }
}
