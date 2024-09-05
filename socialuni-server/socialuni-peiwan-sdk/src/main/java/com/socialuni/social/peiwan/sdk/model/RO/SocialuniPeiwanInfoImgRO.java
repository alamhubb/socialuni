package com.socialuni.social.peiwan.sdk.model.RO;

import com.socialuni.social.common.api.model.SocialuniContentIdRO;
import lombok.Data;

@Data
public class SocialuniPeiwanInfoImgRO extends SocialuniContentIdRO {
    private String src;
    private Integer order;
    private Double aspectRatio;
}
