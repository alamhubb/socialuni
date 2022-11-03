package com.socialuni.social.sdk.logic.factory;

import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import com.socialuni.social.sdk.dao.DO.DistrictDO;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.sdk.logic.platform.MapUtil;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.social.sdk.model.RectangleVO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import org.springframework.stereotype.Component;

@Component
public class TalkDOFactory {
    public static SocialuniTalkDO newTalkDO(SocialuniUserDo user, SocialuniTalkPostQO socialTalkPostQO, DistrictDO district) {
        SocialuniTalkDO talkDO = new SocialuniTalkDO(user.getUnionId(), socialTalkPostQO.getContent());
        Integer talkUnionId = SocialuniUnionIdFacede.createTalkUnionId();

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
        //不要删掉，根据devId查询了动态
        talkDO.setDevId(DevAccountFacade.getDevIdNotNull());
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
