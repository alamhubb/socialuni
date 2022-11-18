package com.socialuni.social.sdk.model.EO;

import com.socialuni.social.user.sdk.model.DO.SocialUserFansDetailDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialProviderLoginEO {
    SocialuniUserDo mineUser;
    //创建或返回
    com.socialuni.social.user.sdk.model.DO.SocialUserFansDetailDo SocialUserFansDetailDo;

    public SocialProviderLoginEO(SocialuniUserDo mineUser, SocialUserFansDetailDo SocialUserFansDetailDo) {
        this.mineUser = mineUser;
        this.SocialUserFansDetailDo = SocialUserFansDetailDo;
    }
}
