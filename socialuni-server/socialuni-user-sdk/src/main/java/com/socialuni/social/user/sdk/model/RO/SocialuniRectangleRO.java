package com.socialuni.social.user.sdk.model.RO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialuniRectangleRO {
    private Double lon;
    private Double lat;
    private String adCode;

    public SocialuniRectangleRO(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }
}
