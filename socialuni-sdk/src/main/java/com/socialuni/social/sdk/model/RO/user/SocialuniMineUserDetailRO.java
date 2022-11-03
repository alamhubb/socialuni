package com.socialuni.social.sdk.model.RO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialuniMineUserDetailRO extends SocialuniUserDetailRO {
    private String phoneNum;
    private String birthday;
    private Integer socialCoin;
    private Boolean openContactInfo;

    public SocialuniMineUserDetailRO(SocialuniMineUserDetailRO userRO) {
        super(userRO);
        this.phoneNum = userRO.getPhoneNum();
        this.birthday = userRO.getBirthday();
        this.socialCoin = userRO.getSocialCoin();
        this.openContactInfo = userRO.getOpenContactInfo();
    }

    public SocialuniMineUserDetailRO(SocialuniUserDetailRO userRO) {
        super(userRO);
    }
}
