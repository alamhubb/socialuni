package com.socialuni.api.model.QO.location;

import lombok.Data;

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
