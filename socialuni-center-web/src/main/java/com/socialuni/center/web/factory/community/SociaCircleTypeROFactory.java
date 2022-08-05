package com.socialuni.center.web.factory.community;

import com.socialuni.center.web.model.DO.tag.TagTypeDO;
import com.socialuni.center.web.model.RO.community.circle.CircleTypeRO;
import com.socialuni.center.web.factory.ListConvertUtil;
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
