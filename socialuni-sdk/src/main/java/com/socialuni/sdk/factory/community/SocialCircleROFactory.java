package com.socialuni.sdk.factory.community;

import com.socialuni.sdk.dao.DO.circle.SocialCircleDO;
import com.socialuni.sdk.model.RO.talk.circle.SocialCircleRO;
import com.socialuni.sdk.factory.ListConvertUtil;

import java.util.List;

public class SocialCircleROFactory {
    public static SocialCircleRO getCircleRO(SocialCircleDO circleDO) {
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

    public static List<SocialCircleRO> circleDOToROS(List<SocialCircleDO> DOs) {
        return ListConvertUtil.toList(SocialCircleROFactory::getCircleRO, DOs);
    }
}
