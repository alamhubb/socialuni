package com.socialuni.social.userImg.model;

import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SocialuniUserImgComRO extends SocialuniUserRO {
    private List<SocialuniUserImgRO> imgs;

    public SocialuniUserImgComRO(SocialuniUserRO socialuniUserDetailRO) {
        super(socialuniUserDetailRO);
    }
}
