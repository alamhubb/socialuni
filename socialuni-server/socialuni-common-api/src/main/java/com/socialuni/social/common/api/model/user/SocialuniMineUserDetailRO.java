package com.socialuni.social.common.api.model.user;

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
    //im一般使用三方服务，需要单独的token
    private String imToken;
    private Integer socialCoin;
    private Boolean openContactInfo;

    public SocialuniMineUserDetailRO(SocialuniMineUserDetailRO userRO) {
        super(userRO);
        this.phoneNum = userRO.getPhoneNum();
        this.imToken = userRO.getImToken();
        this.birthday = userRO.getBirthday();
        this.socialCoin = userRO.getSocialCoin();
        this.openContactInfo = userRO.getOpenContactInfo();
    }

    public SocialuniMineUserDetailRO(SocialuniUserDetailRO userRO) {
        super(userRO);
    }
}
