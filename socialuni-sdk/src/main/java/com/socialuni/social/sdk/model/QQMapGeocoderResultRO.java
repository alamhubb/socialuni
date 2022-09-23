package com.socialuni.social.sdk.model;

import com.socialuni.social.sdk.model.QO.location.LocationQueryDetailRO;
import com.socialuni.social.sdk.model.QO.location.LocationQueryRO;
import lombok.Data;

@Data
public class QQMapGeocoderResultRO {
    LocationQueryRO ad_info;
    LocationQueryDetailRO location;
}
