package com.socialuni.social.tag.factory;

import com.socialuni.social.tag.dao.DO.SocialuniTagTypeDO;
import com.socialuni.social.tag.model.TagTypeRO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialTagTypeROFactory {
    public static TagTypeRO getTagTypeRO(SocialuniTagTypeDO tagTypeDO) {
        TagTypeRO tagTypeRO = new TagTypeRO();
        tagTypeRO.setId(tagTypeDO.getId());
        tagTypeRO.setName(StringUtils.substring(tagTypeDO.getName(), 0, 4));
        tagTypeRO.setCount(tagTypeDO.getCount());
        tagTypeRO.setTalkCount(tagTypeDO.getTalkCount());
        return tagTypeRO;
    }

    public static List<TagTypeRO> tagDOToROS(List<?  extends SocialuniTagTypeDO> DOs) {
        return ListConvertUtil.toList(SocialTagTypeROFactory::getTagTypeRO, DOs);
    }
}
