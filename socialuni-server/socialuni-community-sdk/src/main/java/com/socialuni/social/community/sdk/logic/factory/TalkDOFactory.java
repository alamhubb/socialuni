package com.socialuni.social.community.sdk.logic.factory;

import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkPostQO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.sdk.utils.PositionUtil;
import com.socialuni.social.report.sdk.utils.SocialuniTextContentUtil;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

@Component
public class TalkDOFactory {
    public static SocialuniTalkDO newTalkDO(SocialuniUserDo user, SocialuniTalkPostQO socialTalkPostQO, SocialuniDistrictDO district) {
        SocialuniTalkDO talkDO = new SocialuniTalkDO(user.getUnionId(), socialTalkPostQO.getContent());
        Integer talkUnionId = SocialuniUnionIdFacede.createTalkUnionId();

        talkDO.setUnionId(talkUnionId);

        //设置社交联盟唯一id
//        talkDO.setSocialuniUid(socialTalkPostQO.getSocialuniUid());

//        socialTalkDO.setDevId(DevAccountUtils.getDevId());

        //如果经纬度为空
        if (socialTalkPostQO.getLon() == null || socialTalkPostQO.getLat() == null) {
            //如果经纬度为空
            SocialuniRectangleRO rectangleVO = PositionUtil.getRectangle();
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
