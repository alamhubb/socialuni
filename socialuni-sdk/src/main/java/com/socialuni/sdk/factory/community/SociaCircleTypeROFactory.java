package com.socialuni.sdk.factory.community;

import com.socialuni.sdk.model.DO.tag.TagTypeDO;
import com.socialuni.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.sdk.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SociaCircleTypeROFactory {
    public static CircleTypeRO getCircleTypeRO(TagTypeDO tagTypeDO) {
        CircleTypeRO tagTypeRO = new CircleTypeRO();
        tagTypeRO.setId(tagTypeDO.getId());
        tagTypeRO.setName(StringUtils.substring(tagTypeDO.getName(), 0, 4));
        tagTypeRO.setCount(tagTypeDO.getCount());
        tagTypeRO.setTalkCount(tagTypeDO.getTalkCount());
        return tagTypeRO;
    }

    public static List<CircleTypeRO> tagDOToROS(List<TagTypeDO> DOs) {
        return ListConvertUtil.toList(SociaCircleTypeROFactory::getCircleTypeRO, DOs);
    }
}
