package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniTagAPI;
import com.socialuni.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.sdk.logic.service.tag.CenterTagService;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialuniTagController implements SocialuniTagAPI {
    @Resource
    CenterTagService centerTagAPIImpl;

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
