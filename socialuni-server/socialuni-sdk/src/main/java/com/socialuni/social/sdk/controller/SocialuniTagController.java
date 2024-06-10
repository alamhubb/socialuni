package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.tag.api.SocialuniTagAPI;
import com.socialuni.social.sdk.logic.tag.SocialuniTagService;
import com.socialuni.social.common.sdk.model.QO.tag.TagAddQO;
import com.socialuni.social.tag.model.TagRO;
import com.socialuni.social.tag.model.TagTypeRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/tag")
public class SocialuniTagController implements SocialuniTagAPI {
    @Resource
    SocialuniTagService centerTagAPIImpl;

    @Override
    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        return centerTagAPIImpl.addTag(tagAddQO);
    }

    @Override
    public ResultRO<List<TagRO>> queryTags() {
        return centerTagAPIImpl.queryTags();
    }

    @Override
    public ResultRO<List<TagRO>> queryHotTags() {
        return centerTagAPIImpl.queryHotTags();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        return centerTagAPIImpl.queryTagTypes();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        return centerTagAPIImpl.queryHotTagTypes();
    }
}
