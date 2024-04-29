package com.socialuni.social.follow.api;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO;
import com.socialuni.social.follow.model.follow.SocialuniUserFansDetailRO;
import com.socialuni.social.follow.model.follow.SocialuniUserFollowAddQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

//@RequestMapping("socialuni/follow")
@FeignClient(name = "follow", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/follow")
@Tag(name = "社区模块/关注模块")
public interface SocialuniFollowAPI {

    @PostMapping("queryUserFollows")
    @Operation(summary = "查询用户关注列表")
    ResultRO<List<SocialuniUserFollowDetailListRO>> queryUserFollows(@RequestBody @Valid SocialuniPageQueryQO<String> socialuniPageQueryQO);

    @PostMapping("addFollow")
    @Operation(summary = "关注用户")
    ResultRO<Void> addFollow(@RequestBody @Valid SocialuniUserFollowAddQO addVO);

    @PostMapping("cancelFollow")
    @Operation(summary = "取消关注")
    ResultRO<Void> cancelFollow(@RequestBody @Valid SocialuniUserFollowAddQO addVO);


    @GetMapping("getMineUserFollowDetail")
    @Operation(summary = "粉丝数量详情")
    ResultRO<SocialuniUserFansDetailRO> getMineUserFollowDetail();

}