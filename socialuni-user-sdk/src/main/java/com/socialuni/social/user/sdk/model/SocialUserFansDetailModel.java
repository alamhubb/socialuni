package com.socialuni.social.user.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialUserFansDetailModel implements Serializable {
    //在talk里面展示的放在user里？
    //必有
    private Integer id;
    private Integer userId;
    private Integer fansNum;
    private Integer followNum;

    public SocialUserFansDetailModel(SocialuniUserModel user) {
        this.userId = user.getUnionId();
        this.fansNum = 0;
        this.followNum = 0;
    }
}
