package com.socialuni.center.web.model;

import com.socialuni.api.model.QO.location.LocationQueryDetailRO;
import com.socialuni.api.model.QO.location.LocationQueryRO;
import lombok.Data;

@Data
public class QQMapGeocoderResultRO {
    LocationQueryRO ad_info;
    LocationQueryDetailRO location;
}
