package com.socialuni.social.sdk.dao.utils;

import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.community.sdk.api.TagInterface;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialuniTagDOUtils {
    private static TagInterface tagApi;

    @Resource
    public void setTagRepository(TagInterface tagApi) {
        SocialuniTagDOUtils.tagApi = tagApi;
    }

    public static List<TagModel> save(List<TagModel> tagModels) {
        return tagApi.savePutAll(tagModels);
    }
}
