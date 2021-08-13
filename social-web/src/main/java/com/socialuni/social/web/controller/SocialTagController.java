package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialTagService;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.tag.TagTypeRO;
import com.socialuni.social.sdk.url.community.SocialuniTagUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialTagController implements SocialuniTagUrl {
    @Resource
    SocialTagService socialTagService;

    @Override
    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        return socialTagService.addTag(tagAddQO);
    }

    @Override
    public ResultRO<List<TagRO>> queryTags() {
        return socialTagService.queryTags();
    }

    @Override
    public ResultRO<List<TagRO>> queryHotTags() {
        return socialTagService.queryHotTags();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        return socialTagService.queryTagTypes();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        return socialTagService.queryHotTagTypes();
    }
}
