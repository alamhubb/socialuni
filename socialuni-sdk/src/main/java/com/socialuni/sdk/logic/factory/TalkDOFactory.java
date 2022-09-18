package com.socialuni.sdk.logic.factory;

import com.socialuni.sdk.dao.DO.DistrictDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.logic.platform.MapUtil;
import com.socialuni.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.sdk.model.RectangleVO;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
import org.springframework.stereotype.Component;

@Component
public class TalkDOFactory {
    public static SocialuniTalkDO newTalkDO(SocialuniUserDO user, SocialuniTalkPostQO socialTalkPostQO, DistrictDO district) {
        SocialuniTalkDO talkDO = new SocialuniTalkDO(user.getUnionId(), socialTalkPostQO.getContent());
        Integer talkUnionId = SocialuniUnionIdUtil.createTalkUnionId();

        talkDO.setUnionId(talkUnionId);

        //设置社交联盟唯一id
//        talkDO.setSocialuniUid(socialTalkPostQO.getSocialuniUid());

//        socialTalkDO.setDevId(DevAccountUtils.getDevId());

        //如果经纬度为空
        if (socialTalkPostQO.getLon() == null || socialTalkPostQO.getLat() == null) {
            //如果经纬度为空
            RectangleVO rectangleVO = MapUtil.getRectangle();
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

        SocialuniTextContentUtil.setHasUnderageAndContactInfoByContentText(talkDO.getContent(), talkDO);

        return talkDO;
    }
}
