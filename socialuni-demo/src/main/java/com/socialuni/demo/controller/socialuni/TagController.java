package com.socialuni.demo.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniTagAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.tag.TagTypeRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TagController implements SocialuniTagAPI {
    @Resource
    SocialuniTagAPI socialuniTagAPI;

    @Override
    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        return socialuniTagAPI.addTag(tagAddQO);
    }

    @Override
    public ResultRO<List<TagRO>> queryTags() {
        return socialuniTagAPI.queryTags();
    }

    @Override
    public ResultRO<List<TagRO>> queryHotTags() {
        return socialuniTagAPI.queryHotTags();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        return socialuniTagAPI.queryTagTypes();
    }

    @Override
    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        return socialuniTagAPI.queryHotTagTypes();
    }
}
