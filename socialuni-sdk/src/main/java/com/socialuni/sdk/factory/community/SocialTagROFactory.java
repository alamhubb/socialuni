package com.socialuni.sdk.factory.community;

import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialTagROFactory {
    public static TagRO getTagRO(TagDO tagDO) {
        TagRO tagRO = new TagRO();
        tagRO.setId(tagDO.getId());
        tagRO.setName(StringUtils.substring(tagDO.getName(), 0, 4));
        tagRO.setCount(tagDO.getCount());
        tagRO.setTalkCount(tagDO.getTalkCount());
        tagRO.setAvatar(tagDO.getAvatar());
        tagRO.setDescription( tagDO.getDescription());
        return tagRO;
    }

    public static List<TagRO> tagDOToROS(List<TagDO> DOs) {
        return ListConvertUtil.toList(SocialTagROFactory::getTagRO, DOs);
    }
}
