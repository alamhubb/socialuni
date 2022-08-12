package com.socialuni.sdk.api;

import com.socialuni.sdk.feignAPI.SocialuniTagAPI;
import com.socialuni.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.sdk.serive.CenterTagService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniTagAPIImpl implements SocialuniTagAPI {
    @Resource
    CenterTagService centerTagService;

    @Override
    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        return centerTagService.addTag(tagAddQO);
    }

    @Override
    public ResultRO<List<TagRO>> queryTags() {
        return centerTagService.queryTags();
    }

    @Override
    public ResultRO<List<TagRO>> queryHotTags() {
        return centerTagService.queryHotTags();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        return centerTagService.queryTagTypes();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        return centerTagService.queryHotTagTypes();
    }
}
