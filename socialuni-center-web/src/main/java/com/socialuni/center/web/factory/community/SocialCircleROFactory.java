package com.socialuni.center.web.factory.community;

import com.socialuni.center.web.model.DO.circle.SocialCircleDO;
import com.socialuni.center.web.model.RO.community.circle.SocialCircleRO;
import com.socialuni.center.web.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialCircleROFactory {
    public static SocialCircleRO getCircleRO(SocialCircleDO circleDO) {
        SocialCircleRO circleRO = new SocialCircleRO();
        circleRO.setId(circleDO.getId());
        circleRO.setName(StringUtils.substring(circleDO.getName(), 0, 4));
        circleRO.setCount(circleDO.getCount());
        circleRO.setTalkCount(circleDO.getTalkCount());
        circleRO.setAvatar(circleDO.getAvatar());
        circleRO.setDescription( circleDO.getDescription());
        return circleRO;
    }

    public static List<SocialCircleRO> circleDOToROS(List<SocialCircleDO> DOs) {
        return ListConvertUtil.toList(SocialCircleROFactory::getCircleRO, DOs);
    }
}
