package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.sdk.model.RO.community.tag.TagRO;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialTagROFactory {
    public static TagRO getTagRO(TagModel tagModel) {
        TagRO tagRO = new TagRO();
        tagRO.setId(tagModel.getId());
        tagRO.setName(StringUtils.substring(tagModel.getName(), 0, 4));
        tagRO.setCount(tagModel.getCount());
        tagRO.setTalkCount(tagModel.getTalkCount());
        tagRO.setAvatar(tagModel.getAvatar());
        tagRO.setDescription( tagModel.getDescription());
        return tagRO;
    }

    public static List<TagRO> tagDOToROS(List<?  extends TagModel> DOs) {
        return ListConvertUtil.toList(SocialTagROFactory::getTagRO, DOs);
    }
}
