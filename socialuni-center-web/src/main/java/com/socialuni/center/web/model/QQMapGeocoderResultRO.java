package com.socialuni.center.web.model;

import com.socialuni.center.web.model.QO.location.LocationQueryDetailRO;
import com.socialuni.center.web.model.QO.location.LocationQueryRO;
import lombok.Data;

@Data
public class QQMapGeocoderResultRO {
    LocationQueryRO ad_info;
    LocationQueryDetailRO location;
}
