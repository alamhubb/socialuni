package com.socialuni.sdk.model;

import com.socialuni.sdk.model.QO.location.LocationQueryDetailRO;
import com.socialuni.sdk.model.QO.location.LocationQueryRO;
import lombok.Data;

@Data
public class QQMapGeocoderResultRO {
    LocationQueryRO ad_info;
    LocationQueryDetailRO location;
}
