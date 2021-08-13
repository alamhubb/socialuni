package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniTagAPI;
import com.socialuni.center.web.serive.CenterTagService;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.tag.TagTypeRO;
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
