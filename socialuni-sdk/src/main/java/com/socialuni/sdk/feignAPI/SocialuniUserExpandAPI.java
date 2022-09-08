package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.user.SocialUserSchoolNameEditQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@RequestMapping("socialuni/userExpand")
@Tag(name = "用户模块/用户扩展信息模块")
@FeignClient(name = "userExpand", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniUserExpandAPI {
    @PostMapping("editUserSchool")
    @Operation(summary = "获取用户个人详情")
    ResultRO<SocialuniMineUserDetailRO> editUserSchool(@RequestBody SocialUserSchoolNameEditQO socialMineUserDetailQO);
}