package com.socialuni.social.peiwan.sdk.model.QO;

import lombok.Data;

@Data
public class SocialuniPeiwanInfoRO {
    private String userId;
    private String username;
    private String avatar;

    private String city;
    private String district;
    //纬度
    private Double lat;
    //经度
    private Double lng;
}
