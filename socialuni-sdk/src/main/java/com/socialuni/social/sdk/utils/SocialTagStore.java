package com.socialuni.social.sdk.utils;

import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.sdk.dao.store.SocialTagRedis;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialTagStore {
    @Resource
    SocialTagRedis socialTagRedis;

    public List<?  extends TagModel> findTagsByIds(List<Integer> ids) {
        List<TagModel> tagModels = new ArrayList<>();
        for (Integer id : ids) {
            tagModels.add(socialTagRedis.findTagById(id));
        }
        return tagModels;
    }
}
