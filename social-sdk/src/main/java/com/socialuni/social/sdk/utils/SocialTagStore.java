package com.socialuni.social.sdk.utils;

import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.sdk.store.SocialTagRedis;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialTagStore {
    @Resource
    SocialTagRedis socialTagRedis;

    public List<TagDO> findTagsByIds(List<Integer> ids) {
        List<TagDO> tagDOS = new ArrayList<>();
        for (Integer id : ids) {
            tagDOS.add(socialTagRedis.findTagById(id));
        }
        return tagDOS;
    }
}
