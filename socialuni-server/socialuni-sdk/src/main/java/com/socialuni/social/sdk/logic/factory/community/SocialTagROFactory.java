package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.dao.DO.SocialuniTagDO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.sdk.model.RO.community.tag.TagRO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialTagROFactory {
    public static TagRO getTagRO(SocialuniTagDO TagDO) {
        TagRO tagRO = new TagRO();
        tagRO.setId(TagDO.getId());
        tagRO.setName(TagDO.getName());
        tagRO.setCount(TagDO.getCount());
        tagRO.setTalkCount(TagDO.getTalkCount());
        tagRO.setAvatar(TagDO.getAvatar());
        tagRO.setDescription( TagDO.getDescription());
        return tagRO;
    }

    public static List<TagRO> tagDOToROS(List<?  extends SocialuniTagDO> DOs) {
        return ListConvertUtil.toList(SocialTagROFactory::getTagRO, DOs);
    }
}
