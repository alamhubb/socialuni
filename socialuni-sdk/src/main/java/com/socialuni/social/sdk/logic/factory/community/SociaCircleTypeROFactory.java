package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.model.SocialuniTagTypeModel;
import com.socialuni.social.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SociaCircleTypeROFactory {
    public static CircleTypeRO getCircleTypeRO(SocialuniTagTypeModel tagTypeDO) {
        CircleTypeRO tagTypeRO = new CircleTypeRO();
        tagTypeRO.setId(tagTypeDO.getId());
        tagTypeRO.setName(StringUtils.substring(tagTypeDO.getName(), 0, 4));
        tagTypeRO.setCount(tagTypeDO.getCount());
        tagTypeRO.setTalkCount(tagTypeDO.getTalkCount());
        return tagTypeRO;
    }

    public static List<CircleTypeRO> tagDOToROS(List<?  extends SocialuniTagTypeModel> DOs) {
        return ListConvertUtil.toList(SociaCircleTypeROFactory::getCircleTypeRO, DOs);
    }
}
