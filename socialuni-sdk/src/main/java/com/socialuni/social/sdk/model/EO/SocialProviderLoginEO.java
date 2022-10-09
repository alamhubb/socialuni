package com.socialuni.social.sdk.model.EO;

import com.socialuni.social.user.sdk.model.SocialUserFansDetailModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialProviderLoginEO {
    SocialuniUserModel mineUser;
    //创建或返回
    SocialUserFansDetailModel socialUserFansDetailModel;

    public SocialProviderLoginEO(SocialuniUserModel mineUser, SocialUserFansDetailModel socialUserFansDetailModel) {
        this.mineUser = mineUser;
        this.socialUserFansDetailModel = socialUserFansDetailModel;
    }
}
