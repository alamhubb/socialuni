package com.socialuni.social.common.sdk.model.RO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LocationQueryDetailRO {
    //纬度
    private Double lat;
    //经度
    private Double lng;
}
