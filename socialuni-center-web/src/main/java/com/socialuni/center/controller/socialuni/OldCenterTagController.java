package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.sdk.serive.CenterTagService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tag")
public class OldCenterTagController {
    @Resource
    CenterTagService centerTagAPIImpl;

    @PostMapping("addTag")
    public ResultRO<TagRO> addTag(@RequestBody @Valid TagAddQO tagAddQO) {
        return centerTagAPIImpl.addTag(tagAddQO);
    }

    @PostMapping("queryTags")
    public ResultRO<List<TagRO>> queryTags() {
        return centerTagAPIImpl.queryTags();
    }

    @PostMapping("queryHotTags")
    public ResultRO<List<TagRO>> queryHotTags() {
        return centerTagAPIImpl.queryHotTags();
    }

    @PostMapping("queryTagTypes")
    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        return centerTagAPIImpl.queryTagTypes();
    }

    @PostMapping("queryHotTagTypes")
    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        return centerTagAPIImpl.queryHotTagTypes();
    }
}
