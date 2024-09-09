package com.socialuni.social.userImg.model;

import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SocialuniUserImgComRO extends SocialuniUserShowRO {
    private List<SocialuniUserImgRO> imgs;

    public SocialuniUserImgComRO(SocialuniUserShowRO socialuniUserDetailRO) {
        super(socialuniUserDetailRO);
    }
}
