package com.socialuni.center.web.feignAPI;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.QO.community.tag.TagAddQO;
import com.socialuni.center.web.model.RO.community.tag.TagRO;
import com.socialuni.center.web.model.RO.community.tag.TagTypeRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("tag")
@FeignClient(name = "tag", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniTagAPI {
    @PostMapping("addTag")
    ResultRO<TagRO> addTag(@RequestBody @Valid TagAddQO tagAddVO);

    @PostMapping("queryTags")
    ResultRO<List<TagRO>> queryTags();

    @PostMapping("queryHotTags")
    ResultRO<List<TagRO>> queryHotTags();

    @PostMapping("queryTagTypes")
    ResultRO<List<TagTypeRO>> queryTagTypes();

    //查询热门tag，系统首次加载时使用的
    @PostMapping("queryHotTagTypes")
    ResultRO<List<TagTypeRO>> queryHotTagTypes();
}