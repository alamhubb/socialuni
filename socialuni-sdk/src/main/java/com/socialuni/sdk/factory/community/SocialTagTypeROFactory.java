package com.socialuni.sdk.factory.community;

import com.socialuni.sdk.model.DO.tag.TagTypeDO;
import com.socialuni.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.sdk.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialTagTypeROFactory {
    public static TagTypeRO getTagTypeRO(TagTypeDO tagTypeDO) {
        TagTypeRO tagTypeRO = new TagTypeRO();
        tagTypeRO.setId(tagTypeDO.getId());
        tagTypeRO.setName(StringUtils.substring(tagTypeDO.getName(), 0, 4));
        tagTypeRO.setCount(tagTypeDO.getCount());
        tagTypeRO.setTalkCount(tagTypeDO.getTalkCount());
        return tagTypeRO;
    }

    public static List<TagTypeRO> tagDOToROS(List<TagTypeDO> DOs) {
        return ListConvertUtil.toList(SocialTagTypeROFactory::getTagTypeRO, DOs);
    }
}
