package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.model.SocialuniCircleModel;
import com.socialuni.social.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;

import java.util.List;

public class SocialCircleROFactory {
    public static SocialCircleRO getCircleRO(SocialuniCircleModel circleDO) {
        SocialCircleRO circleRO = new SocialCircleRO();
        circleRO.setId(circleDO.getId());
        circleRO.setName(circleDO.getName());
//        circleRO.setName(StringUtils.substring(circleDO.getName(), 0, 4));
        circleRO.setCount(circleDO.getCount());
        circleRO.setTalkCount(circleDO.getTalkCount());
        circleRO.setAvatar(circleDO.getAvatar());
        circleRO.setDescription(circleDO.getDescription());
        return circleRO;
    }

    public static List<SocialCircleRO> circleDOToROS(List<?  extends SocialuniCircleModel> DOs) {
        return ListConvertUtil.toList(SocialCircleROFactory::getCircleRO, DOs);
    }
}
