package com.socialuni.social.common.sdk.model.RO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class LocationQueryRO {
    private String adcode;
    private String city;
    private String city_code;
    private String district;
    private String name;
    private String nation;
    private String nation_code;
    private String province;
    private LocationQueryDetailRO location;
}
