package com.socialuni.sdk.logic.platform;

import com.socialuni.sdk.model.QO.location.LocationQueryQO;
import com.socialuni.sdk.model.QO.location.LocationQueryRO;
import com.socialuni.sdk.model.QQMapGeocoderRO;
import com.socialuni.sdk.model.QQMapGeocoderResultRO;
import com.socialuni.sdk.utils.common.RestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class QQMapAPI {
    private final static String map_geocoder_url = "https://apis.map.qq.com/ws/geocoder/v1/?key=";
    private final static String map_ip_url = "https://apis.map.qq.com/ws/location/v1/ip?key=";
    private static String map_web_key;

    @Value("${socialuni.map.qq.secret-key}")
    public void setMap_web_key(String map_web_key) {
        QQMapAPI.map_web_key = map_web_key;
    }

    public static LocationQueryRO geocoder(LocationQueryQO queryQO) {
        ResponseEntity<QQMapGeocoderRO> responseEntity = RestUtil.getFileRestTemplate().getForEntity(map_geocoder_url + map_web_key + "&location=" + queryQO.getLatitude() + "," + queryQO.getLongitude(), QQMapGeocoderRO.class);
        QQMapGeocoderRO qqMapGeocoderRO = responseEntity.getBody();
        if (qqMapGeocoderRO == null) {
            return null;
        }
        QQMapGeocoderResultRO qqMapGeocoderResultRO = qqMapGeocoderRO.getResult();
        if (qqMapGeocoderResultRO == null) {
            return null;
        }
        return qqMapGeocoderResultRO.getAd_info();
    }

    public static LocationQueryRO getIpLocation(String ip) {
        ResponseEntity<QQMapGeocoderRO> responseEntity = RestUtil.getFileRestTemplate().getForEntity(map_ip_url + map_web_key + "&ip=" + ip, QQMapGeocoderRO.class);
        QQMapGeocoderRO qqMapGeocoderRO = responseEntity.getBody();
        if (qqMapGeocoderRO == null) {
            return null;
        }
        QQMapGeocoderResultRO qqMapGeocoderResultRO = qqMapGeocoderRO.getResult();
        if (qqMapGeocoderResultRO == null) {
            return null;
        }
        //没有返回location，需要使用外层的
        LocationQueryRO locationQueryRO = qqMapGeocoderResultRO.getAd_info();
        locationQueryRO.setLocation(qqMapGeocoderResultRO.getLocation());
        return locationQueryRO;
    }
}
