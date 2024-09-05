package com.socialuni.social.common.api.model.user;


import com.socialuni.social.common.api.model.SocialuniContentIdRO;
import lombok.Data;

@Data
public class SocialuniUserImgRO extends SocialuniContentIdRO {
    private String src;
    private Double aspectRatio;
}
