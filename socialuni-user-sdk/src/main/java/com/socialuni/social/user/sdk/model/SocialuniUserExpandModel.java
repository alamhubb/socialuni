package com.socialuni.social.user.sdk.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUserExpandModel extends SocialuniContentBaseModel implements Serializable {
    private Integer userId;
    private String schoolName;
    private String contactInfo;

    //开启了可获取联系方式
    private Boolean openContactInfo;
}