package com.socialuni.social.sdk.model.EO;

import com.socialuni.social.sdk.dao.DO.user.SocialUserFansDetailDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialProviderLoginEO {
    SocialuniUserDO mineUser;
    //创建或返回
    SocialUserFansDetailDO socialUserFansDetailDO;

    public SocialProviderLoginEO(SocialuniUserDO mineUser, SocialUserFansDetailDO socialUserFansDetailDO) {
        this.mineUser = mineUser;
        this.socialUserFansDetailDO = socialUserFansDetailDO;
    }
}