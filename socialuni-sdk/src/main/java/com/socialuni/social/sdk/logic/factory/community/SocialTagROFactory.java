package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import com.socialuni.social.sdk.model.RO.community.tag.TagRO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialTagROFactory {
    public static TagRO getTagRO(TagDO TagDO) {
        TagRO tagRO = new TagRO();
        tagRO.setId(TagDO.getId());
        tagRO.setName(StringUtils.substring(TagDO.getName(), 0, 4));
        tagRO.setCount(TagDO.getCount());
        tagRO.setTalkCount(TagDO.getTalkCount());
        tagRO.setAvatar(TagDO.getAvatar());
        tagRO.setDescription( TagDO.getDescription());
        return tagRO;
    }

    public static List<TagRO> tagDOToROS(List<?  extends TagDO> DOs) {
        return ListConvertUtil.toList(SocialTagROFactory::getTagRO, DOs);
    }
}
