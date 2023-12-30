package com.socialuni.social.common.sdk.model.QO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LocationQueryQO {
    //ip
    private String ip;
    //纬度
    private String latitude;
    //经度
    private String longitude;
}
