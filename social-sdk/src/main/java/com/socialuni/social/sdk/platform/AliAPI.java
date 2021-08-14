package com.socialuni.social.sdk.platform;

import com.socialuni.social.sdk.model.IpResult;
import com.socialuni.social.sdk.model.RectangleVO;
import social.web.sdk.utils.IpUtil;
import com.socialuni.social.sdk.utils.SocialHeaderUtil;
import com.socialuni.social.sdk.utils.common.RestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AliAPI {
    private static String map_web_key;

    @Value("${socialuni.map.ali.secret-key}")
    public  void setMap_web_key(String map_web_key) {
        AliAPI.map_web_key = map_web_key;
    }

    public static RectangleVO getRectangle() {
        HttpServletRequest request = SocialHeaderUtil.getRequest();
        String ip = IpUtil.getIpAddr(request);

        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是WinDOws系统
            ip = "125.33.221.85";
        }
        ResponseEntity<IpResult> responseEntity = RestUtil.restTemplate().getForEntity(AliConst.map_ip_url  + map_web_key + "&ip="+ ip, IpResult.class);
        IpResult res = responseEntity.getBody();
        assert res != null;
        RectangleVO rectangleVO = new RectangleVO();
        if (!res.hasError()) {
           /* DistrictVO district = new DistrictVO();
            district.setAdCode(res.adcode);
            district.setProvinceName(res.province);
            if (!res.province.equals(res.city)) {
                district.setCityName(res.city);
            }
            district.setAdName(res.city);*/
            String rectangle = (String) res.rectangle;
            String[] rectangleAry = rectangle.split(";");
            String[] lonAndLatAry = rectangleAry[0].split(",");
            rectangleVO.setLon(Double.valueOf(lonAndLatAry[0]));
            rectangleVO.setLat(Double.valueOf(lonAndLatAry[1]));
            rectangleVO.setAdCode((String) res.adcode);
            return rectangleVO;
        }
        return null;
    }


}
