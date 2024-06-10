package com.socialuni.social.tag.factory;

import com.socialuni.social.tag.dao.DO.SocialuniTagTypeDO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.tag.model.CircleTypeRO;
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

    public static List<CircleTypeRO> tagDOToROS(List<?  extends SocialuniTagTypeDO> DOs) {
        return ListConvertUtil.toList(SociaCircleTypeROFactory::getCircleTypeRO, DOs);
    }
}
