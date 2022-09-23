package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.sdk.dao.DO.tag.TagDO;
import com.socialuni.social.sdk.dao.repository.community.TagRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialuniTagDOUtils {
    private static TagRepository tagRepository;

    @Resource
    public void setTagRepository(TagRepository tagRepository) {
        SocialuniTagDOUtils.tagRepository = tagRepository;
    }

    public static List<TagDO> save(List<TagDO> tagDOS) {
        return tagRepository.saveAll(tagDOS);
    }
}
