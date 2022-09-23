package com.socialuni.social.sdk.feignAPI.user;

import com.socialuni.social.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@RequestMapping("socialuni/userExpand")
@Tag(name = "用户模块/用户扩展信息模块")
@FeignClient(name = "userExpand", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniUserExpandAPI {
    @PostMapping("editUserSchool")
    @Operation(summary = "编辑用户学校名称")
    ResultRO<SocialuniMineUserDetailRO> editUserSchool(@RequestBody SocialUserSchoolNameEditQO socialMineUserDetailQO);

    @PostMapping("editUserContactInfo")
    @Operation(summary = "编辑用户联系方式")
    ResultRO<SocialuniMineUserDetailRO> editUserContactInfo(@RequestBody SocialUserContactInfoEditQO socialuniMineUserDetailRO);
}