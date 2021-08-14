package com.socialuni.social.sdk.manage;

import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.sdk.repository.TagRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialTagManage {
    @Resource
    private TagRepository tagRepository;

    public TagDO createTagDO(Integer userId, TagAddQO tagAddQO) {
        TagDO tagDO = new TagDO(userId, tagAddQO.getTagName(), tagAddQO.getDescription());
        tagDO = tagRepository.save(tagDO);
        return tagDO;
    }
}
