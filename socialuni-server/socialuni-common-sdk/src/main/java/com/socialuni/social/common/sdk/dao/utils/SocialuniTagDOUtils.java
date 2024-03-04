package com.socialuni.social.common.sdk.dao.utils;

import com.socialuni.social.community.sdk.dao.DO.SocialuniTagDO;
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

    public static List<SocialuniTagDO> save(List<SocialuniTagDO> TagDOs) {
        return tagApi.savePutAll(TagDOs);
    }
}
