package com.socialuni.center.web.factory;

import com.socialuni.center.web.model.RectangleVO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.DO.DistrictDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.center.web.model.QO.community.talk.SocialTalkImgAddQO;
import com.socialuni.center.web.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.center.web.platform.MapUtil;
import com.socialuni.center.web.utils.ImgCheckUtil;
import com.socialuni.center.web.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TalkDOFactory {
    public static SocialTalkDO newTalkDO(SocialUserDO user, SocialTalkPostQO socialTalkPostQO, DistrictDO district) {
        SocialTalkDO talkDO = new SocialTalkDO(user.getUnionId(), socialTalkPostQO.getContent());

        talkDO.setDevId(socialTalkPostQO.getDevId());
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

        //是否包含图片
        if (socialTalkPostQO.getImgs().size() > 0) {
            List<SocialTalkImgAddQO> imgs = socialTalkPostQO.getImgs();
            for (SocialTalkImgAddQO img : imgs) {
                Boolean hasPeople = ImgCheckUtil.hasPeopleImg(img.getSrc());
                if (hasPeople) {
                    talkDO.setHasPeopleImg(true);
                    break;
                }
            }
        }

        Boolean userIdentityAuth = SocialUserUtil.getUserIsIdentityAuth(user.getUnionId());
        //如果存在人物图像，则不可发表
        if (talkDO.getHasPeopleImg()) {
            if (!userIdentityAuth) {
                throw new SocialBusinessException("完成成年认证，才能发布包含人物图像的图片");
            }
        }


        //是否已经认证
        talkDO.setIdentityAuth(SocialUserUtil.getUserIsIdentityAuth(user.getUnionId()));

        Integer talkUnionId = UnionIdDbUtil.createTalkUuid();
        talkDO.setUnionId(talkUnionId);
        return talkDO;
    }
}
