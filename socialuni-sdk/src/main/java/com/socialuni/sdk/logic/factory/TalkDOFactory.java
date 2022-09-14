package com.socialuni.sdk.logic.factory;

import com.socialuni.sdk.model.RectangleVO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.DistrictDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.logic.platform.MapUtil;
import com.socialuni.sdk.utils.ImgCheckUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkImgAddQO;
import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TalkDOFactory {
    public static SocialTalkDO newTalkDO(SocialuniUserDO user, SocialuniTalkPostQO socialTalkPostQO, DistrictDO district) {
        SocialTalkDO talkDO = new SocialTalkDO(user.getUnionId(), socialTalkPostQO.getContent());

        //设置社交联盟唯一id
        talkDO.setSocialuniUid(socialTalkPostQO.getSocialuniUid());

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

        Integer talkUnionId = UnionIdDbUtil.createTalkUnionId();
        talkDO.setUnionId(talkUnionId);
        return talkDO;
    }
}
