package com.socialuni.social.common.sdk.feignAPI.community;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.QO.tag.TagAddQO;
import com.socialuni.social.common.sdk.model.RO.tag.TagRO;
import com.socialuni.social.common.sdk.model.RO.tag.TagTypeRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

//@RequestMapping("socialuni/tag")
@Tag(name = "社区模块/话题模块")
@FeignClient(name = "tag", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/tag")
public interface SocialuniTagAPI {
    @PostMapping("addTag")
    @Operation(summary = "添加话题")
    ResultRO<TagRO> addTag(@RequestBody @Valid TagAddQO tagAddQO);

    @GetMapping("queryTags")
    @Operation(summary = "查询所有话题")
    ResultRO<List<TagRO>> queryTags();

    @GetMapping("queryHotTags")
    @Operation(summary = "查询热门话题")
    ResultRO<List<TagRO>> queryHotTags();

    @GetMapping("queryTagTypes")
    @Operation(summary = "查询所有话题分类")
    ResultRO<List<TagTypeRO>> queryTagTypes();

    //查询热门tag，系统首次加载时使用的
    @GetMapping("queryHotTagTypes")
    @Operation(summary = "查询热门话题分类")
    ResultRO<List<TagTypeRO>> queryHotTagTypes();
}