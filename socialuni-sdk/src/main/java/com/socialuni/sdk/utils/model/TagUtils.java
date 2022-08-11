package com.socialuni.sdk.utils.model;

import com.socialuni.sdk.model.DO.tag.TagDO;
import com.socialuni.sdk.repository.community.TagRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TagUtils {
    private static TagRepository tagRepository;

    @Resource
    public void setTagRepository(TagRepository tagRepository) {
        TagUtils.tagRepository = tagRepository;
    }

    public static List<TagDO> save(List<TagDO> tagDOS) {
        return tagRepository.saveAll(tagDOS);
    }
}
