package com.socialuni.social.sdk.feignAPI.user;

import com.socialuni.social.sdk.model.QO.user.SocialuniUserIdQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


//@RequestMapping("socialuni/userExpand")
@Tag(name = "用户模块/用户扩展信息模块")
@FeignClient(name = "userExpand", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/userExpand")
public interface SocialuniUserExpandAPI {
    @PostMapping("editUserSchool")
    @Operation(summary = "编辑用户学校名称")
    ResultRO<SocialuniMineUserDetailRO> editUserSchool(@RequestBody SocialUserSchoolNameEditQO socialMineUserDetailQO);

    @PostMapping("editUserContactInfo")
    @Operation(summary = "编辑用户联系方式")
    ResultRO<SocialuniMineUserDetailRO> editUserContactInfo(@RequestBody SocialUserContactInfoEditQO socialuniMineUserDetailRO);

    @PostMapping("switchOpenUserContactInfo")
    @Operation(summary = "用户切换他人是否可获取联系方式功能")
    ResultRO<SocialuniMineUserDetailRO> switchOpenUserContactInfo(@RequestBody SocialUserContactInfoEditQO socialuniMineUserDetailRO);

    @PostMapping("getUserContactInfo")
    @Operation(summary = "获取用户联系方式")
    ResultRO<SocialuniMineUserDetailRO> getUserContactInfo(@RequestBody SocialuniUserIdQO socialuniMineUserDetailRO);
}