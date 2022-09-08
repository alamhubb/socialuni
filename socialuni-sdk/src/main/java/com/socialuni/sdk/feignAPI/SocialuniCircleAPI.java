package com.socialuni.sdk.feignAPI;


import com.socialuni.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.talk.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.talk.circle.SocialCircleRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("socialuni/circle")
@FeignClient(name = "circle", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "社区模块/圈子模块")
public interface SocialuniCircleAPI {
    @PostMapping("createCircle")
    @Operation(summary = "创建圈子")
    ResultRO<SocialCircleRO> createCircle(@RequestBody @Valid CircleCreateQO circleCreateQO);

    @GetMapping("queryHotCircles")
    @Operation(summary = "查询热门圈子")
    ResultRO<List<SocialCircleRO>> queryHotCircles();

    @GetMapping("queryCircleTypes")
    @Operation(summary = "查询所有圈子分类")
    ResultRO<List<CircleTypeRO>> queryCircleTypes();

    @PostMapping("queryCirclesByCircleType")
    @Operation(summary = "根据圈子分类名称查询分类下的所有圈子")
    ResultRO<List<SocialCircleRO>> queryCirclesByCircleType(@RequestBody @Valid SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO);

}

