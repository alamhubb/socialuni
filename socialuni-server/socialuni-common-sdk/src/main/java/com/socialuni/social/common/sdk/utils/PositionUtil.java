package com.socialuni.social.common.sdk.utils;

import com.qingchi.qing.utils.IpUtil;
import com.socialuni.social.common.sdk.model.RO.LocationQueryRO;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import com.socialuni.social.common.sdk.platform.QQMapAPI;

public class PositionUtil {
    public static SocialuniRectangleRO getRectangle() {
        String ip = IpUtil.getIpAddr();
        LocationQueryRO locationQueryRO = QQMapAPI.getIpLocation(ip);
        return PositionUtil.getRectangle(locationQueryRO);
    }

    public static SocialuniRectangleRO getRectangle(LocationQueryRO locationQueryRO) {
        if (locationQueryRO == null || locationQueryRO.getLocation() == null) {
            return null;
        }
        SocialuniRectangleRO rectangleVO = new SocialuniRectangleRO();
        rectangleVO.setLon(locationQueryRO.getLocation().getLng());
        rectangleVO.setLat(locationQueryRO.getLocation().getLat());
        rectangleVO.setAdCode(locationQueryRO.getAdcode());
        return rectangleVO;
    }

    public static Double getDistance(SocialuniRectangleRO dataRO) {
        String lon = SocialuniRequestUtil.getCityLon();
        String lat = SocialuniRequestUtil.getCityLat();
        Double dbLon = null;
        Double dbLat = null;
        if (lon != null) {
            dbLon = Double.valueOf(lon);
        }
        if (lat != null) {
            dbLat = Double.valueOf(lat);
        }
        SocialuniRectangleRO socialuniRectangleRO = new SocialuniRectangleRO(dbLon, dbLat);
        return PositionUtil.getDistance(socialuniRectangleRO, dataRO);
    }

    public static Double getDistance(SocialuniRectangleRO queryVO, SocialuniRectangleRO dataRO) {
        Double lon = queryVO.getLon();
        Double lat = queryVO.getLat();
        if (lon == null || lat == null) {
            queryVO = PositionUtil.getRectangle();
            if (queryVO == null) {
                return null;
            }
            lon = queryVO.getLon();
            lat = queryVO.getLat();
        }
        if (lon == null || lat == null) {
            return null;
        }
        Double dbLon = dataRO.getLon();
        Double dbLat = dataRO.getLat();
        //如果talk有记录经纬度
        if (dbLon == null || dbLat == null) {
            return null;
        }
        //经纬度换约等于大概换算成千米，任何地点经度都大致相等
        Double talkLon = dbLon * 111;
        Double talkLat = dbLat * (Math.cos(Math.toRadians(dbLat)) * 111);
        //任何地点经度都大致相等,为111公里
        Double queryLon = lon * 111;
        //计算当前纬度，1纬度等于多少公里
        Double queryLat = lat * (Math.cos(Math.toRadians(lat)) * 111);
        //两个经纬度求差
        double lonDiffAbs = Math.abs(talkLon - queryLon);
        double latDiffAbs = Math.abs(talkLat - queryLat);
        //经纬度差勾股求距离
        Double distance = Math.sqrt(Math.pow(lonDiffAbs, 2) + Math.pow(latDiffAbs, 2));
        return distance;
    }
}
