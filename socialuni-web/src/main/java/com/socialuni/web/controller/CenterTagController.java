package com.socialuni.web.controller;

import com.socialuni.sdk.feignAPI.SocialuniTagAPI;
import com.socialuni.sdk.serive.CenterTagService;
import com.socialuni.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.model.RO.community.tag.TagTypeRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterTagController implements SocialuniTagAPI {
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
