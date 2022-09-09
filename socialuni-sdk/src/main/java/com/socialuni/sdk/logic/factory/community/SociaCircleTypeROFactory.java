package com.socialuni.sdk.logic.factory.community;

import com.socialuni.sdk.dao.DO.tag.SocialuniTagTypeDO;
import com.socialuni.sdk.model.RO.talk.circle.CircleTypeRO;
import com.socialuni.sdk.logic.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SociaCircleTypeROFactory {
    public static CircleTypeRO getCircleTypeRO(SocialuniTagTypeDO tagTypeDO) {
        CircleTypeRO tagTypeRO = new CircleTypeRO();
        tagTypeRO.setId(tagTypeDO.getId());
        tagTypeRO.setName(StringUtils.substring(tagTypeDO.getName(), 0, 4));
        tagTypeRO.setCount(tagTypeDO.getCount());
        tagTypeRO.setTalkCount(tagTypeDO.getTalkCount());
        return tagTypeRO;
    }

    public static List<CircleTypeRO> tagDOToROS(List<SocialuniTagTypeDO> DOs) {
        return ListConvertUtil.toList(SociaCircleTypeROFactory::getCircleTypeRO, DOs);
    }
}
