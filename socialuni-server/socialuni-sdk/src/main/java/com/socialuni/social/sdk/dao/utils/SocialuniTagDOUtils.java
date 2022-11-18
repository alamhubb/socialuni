package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.community.sdk.repository.TagRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialuniTagDOUtils {
    private static TagRepository tagApi;

    @Resource
    public void setTagRepository(TagRepository tagApi) {
        SocialuniTagDOUtils.tagApi = tagApi;
    }

    public static List<TagDO> save(List<TagDO> TagDOs) {
        return tagApi.savePutAll(TagDOs);
    }
}
