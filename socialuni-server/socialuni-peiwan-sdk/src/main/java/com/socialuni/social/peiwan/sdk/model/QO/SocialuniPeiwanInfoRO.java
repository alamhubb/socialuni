package com.socialuni.social.peiwan.sdk.model.QO;

import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanInfoImgRO;
import lombok.Data;

import java.util.List;

@Data
public class SocialuniPeiwanInfoRO  {
    private String userId;
    private String nickname;
    private String avatar;

    private String city;
    private String district;
    //纬度
    private Double lat;
    //经度
    private Double lng;
    private Double distance;
    private List<String> skills;

    private List<SocialuniPeiwanInfoImgRO> imgs;
}
