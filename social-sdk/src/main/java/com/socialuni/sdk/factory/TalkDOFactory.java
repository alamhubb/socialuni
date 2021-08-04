package com.socialuni.sdk.factory;

import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.model.DO.DistrictDO;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.RectangleVO;
import com.socialuni.sdk.platform.AliAPI;
import org.springframework.stereotype.Component;

@Component
public class TalkDOFactory {
    public static TalkDO newTalkDO(UserDO user, SocialTalkPostQO socialTalkPostQO, DistrictDO district) {
        TalkDO talkDO = new TalkDO(user.getId(), socialTalkPostQO.getContent());

//        socialTalkDO.setDevId(DevAccountUtils.getDevId());

        //如果经纬度为空
        if (socialTalkPostQO.getLon() == null || socialTalkPostQO.getLat() == null) {
            //如果经纬度为空
            RectangleVO rectangleVO = AliAPI.getRectangle();
            if (rectangleVO != null) {
                socialTalkPostQO.setLon(rectangleVO.getLon());
                socialTalkPostQO.setLat(rectangleVO.getLat());
            }
        }
//        PositionDO positionDO = new PositionDO(user.getId(), districtDO, this.lon, this.lat);
//        positionDO = PositionUtils.save(positionDO);
//        talkDO.setPositionId(positionDO.getId());

        //使用talk本身存储,position 和 district
        talkDO.setAdCode(district.getAdCode());
        talkDO.setAdName(district.getAdName());
        talkDO.setProvinceName(district.getProvinceName());
        talkDO.setCityName(district.getCityName());
        talkDO.setDistrictName(district.getDistrictName());
        talkDO.setLon(socialTalkPostQO.getLon());
        talkDO.setLat(socialTalkPostQO.getLat());
        talkDO.setVisibleGender(socialTalkPostQO.getVisibleGender());
        talkDO.setVisibleType(socialTalkPostQO.getVisibleType());


        return talkDO;
    }
}
