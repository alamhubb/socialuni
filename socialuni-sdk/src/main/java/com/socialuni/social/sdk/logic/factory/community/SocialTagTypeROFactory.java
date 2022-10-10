package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.model.SocialuniTagTypeModel;
import com.socialuni.social.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialTagTypeROFactory {
    public static TagTypeRO getTagTypeRO(SocialuniTagTypeModel tagTypeDO) {
        TagTypeRO tagTypeRO = new TagTypeRO();
        tagTypeRO.setId(tagTypeDO.getId());
        tagTypeRO.setName(StringUtils.substring(tagTypeDO.getName(), 0, 4));
        tagTypeRO.setCount(tagTypeDO.getCount());
        tagTypeRO.setTalkCount(tagTypeDO.getTalkCount());
        return tagTypeRO;
    }

    public static List<TagTypeRO> tagDOToROS(List<?  extends SocialuniTagTypeModel> DOs) {
        return ListConvertUtil.toList(SocialTagTypeROFactory::getTagTypeRO, DOs);
    }
}
