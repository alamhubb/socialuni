package com.socialuni.social.user.sdk.model.RO;

import com.socialuni.social.user.sdk.model.RO.LocationQueryDetailRO;
import com.socialuni.social.user.sdk.model.RO.LocationQueryRO;
import lombok.Data;

@Data
public class QQMapGeocoderResultRO {
    LocationQueryRO ad_info;
    LocationQueryDetailRO location;
}
