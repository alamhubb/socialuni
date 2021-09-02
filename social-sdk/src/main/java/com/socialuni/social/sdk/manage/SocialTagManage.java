package com.socialuni.social.sdk.manage;

import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.sdk.factory.SocialTagDOFactory;
import com.socialuni.social.sdk.repository.TagRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialTagManage {
    @Resource
    private TagRepository tagRepository;

    public TagDO createTagDO(TagAddQO tagAddQO, Integer mineUserId) {
        TagDO tagDO = SocialTagDOFactory.toTagDO(tagAddQO.getTagName(), tagAddQO.getDescription(), mineUserId);
        tagDO = tagRepository.save(tagDO);
        return tagDO;
    }
}
