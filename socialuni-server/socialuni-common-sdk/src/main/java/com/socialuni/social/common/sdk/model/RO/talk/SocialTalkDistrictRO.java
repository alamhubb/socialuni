package com.socialuni.social.common.sdk.model.RO.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan
 * @date 2019-10-31 21:31
 */
@Data
@NoArgsConstructor
public class SocialTalkDistrictRO {
    //统一标识
    private String adCode;
    //省
    private String provinceName;
    //市
    private String cityName;
    //区县
    private String districtName;

    public SocialTalkDistrictRO(String adCode, String provinceName, String cityName, String districtName) {
        this.adCode = adCode;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.districtName = districtName;
    }
}
