package com.socialuni.social.sdk.utils;

import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.sdk.repository.TagRepository;
import com.socialuni.social.sdk.store.SocialTagStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TagStoreUtils {
    @Resource
    SocialTagStore socialTagStore;
    @Resource
    TagRepository tagRepository;

    //获取talk下的
    public List<TagDO> getTagsByTalkId(Integer talkId) {
        List<Integer> tagIds = socialTagStore.getTagIdsByTalkId(talkId);
        return findTagsByIds(tagIds);
    }

    public TagDO findById(Integer tagId) {
        Optional<TagDO> tagDOOptional = tagRepository.findById(tagId);
        return tagDOOptional.orElse(null);
    }

    public TagDO save(TagDO tagDO) {
        return tagRepository.save(tagDO);
    }


    private List<TagDO> findTagsByIds(List<Integer> ids) {
        List<TagDO> tagDOS = new ArrayList<>();
        for (Integer id : ids) {
            tagDOS.add(findById(id));
        }
        return tagDOS;
    }
}
