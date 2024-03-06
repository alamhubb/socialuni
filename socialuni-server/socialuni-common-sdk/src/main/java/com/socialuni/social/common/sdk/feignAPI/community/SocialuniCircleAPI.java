package com.socialuni.social.common.sdk.feignAPI.community;


import com.socialuni.social.common.sdk.model.QO.circle.CircleChatCreateQO;
import com.socialuni.social.common.sdk.model.QO.circle.CircleCreateQO;
import com.socialuni.social.common.sdk.model.QO.circle.CircleTalkTabInfoQO;
import com.socialuni.social.common.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.common.sdk.model.RO.SocialuniTalkTabCircleRO;
import com.socialuni.social.common.sdk.model.RO.circle.CircleTypeRO;
import com.socialuni.social.common.sdk.model.RO.SocialCircleRO;
import com.socialuni.social.common.api.model.ResultRO;
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

//@RequestMapping("socialuni/circle")
@FeignClient(name = "circle", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/circle")
@Tag(name = "社区模块/圈子模块")
public interface SocialuniCircleAPI {
    @PostMapping("createCircle")
    @Operation(summary = "创建圈子")
    ResultRO<SocialCircleRO> createCircle(@RequestBody @Valid CircleCreateQO circleCreateQO);

    @PostMapping("createCircleChat")
    @Operation(summary = "创建圈子群聊")
    ResultRO<String> createCircleChat(@RequestBody @Valid CircleChatCreateQO circleChatCreateQO);

    @PostMapping("queryCircleTalkTabInfo")
    @Operation(summary = "查询圈子动态的信息")
    ResultRO<SocialuniTalkTabCircleRO> queryCircleTalkTabInfo(@RequestBody @Valid CircleTalkTabInfoQO circleTalkTabInfoQO);

    @GetMapping("queryHotCircles")
    @Operation(summary = "查询热门圈子")
    ResultRO<List<SocialCircleRO>> queryHotCircles();

    @GetMapping("queryCircleTypes")
    @Operation(summary = "查询所有圈子分类")
    ResultRO<List<CircleTypeRO>> queryCircleTypes();

    @GetMapping("queryHotCircleTypes")
    @Operation(summary = "查询所有圈子分类")
    ResultRO<List<CircleTypeRO>> queryHotCircleTypes();

    @PostMapping("queryCirclesByCircleType")
    @Operation(summary = "根据圈子分类名称查询分类下的所有圈子")
    ResultRO<List<SocialCircleRO>> queryCirclesByCircleType(@RequestBody @Valid SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO);

}

