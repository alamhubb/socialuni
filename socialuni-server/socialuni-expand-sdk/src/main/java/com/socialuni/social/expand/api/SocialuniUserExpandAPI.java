package com.socialuni.social.expand.api;

import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserExtendDetailRO;
import com.socialuni.social.expand.model.SocialuniUserExpandDetailEditRO;
import com.socialuni.social.user.sdk.model.QO.SocialUserContactInfoEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserSchoolNameEditQO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.QO.user.SocialuniUserExtendFriendQueryQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


//@RequestMapping("socialuni/userExpand")
@Tag(name = "用户模块/用户扩展信息模块")
@FeignClient(name = "userExpand", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/userExpand")
public interface SocialuniUserExpandAPI {
    @PostMapping("editUserSchool")
    @Operation(summary = "编辑用户学校名称")
    ResultRO<SocialuniUserExpandDetailEditRO> editUserSchool(@RequestBody SocialUserSchoolNameEditQO socialMineUserDetailQO);

    @PostMapping("editUserContactInfo")
    @Operation(summary = "编辑用户联系方式")
    ResultRO<SocialuniUserExpandDetailEditRO> editUserContactInfo(@RequestBody SocialUserContactInfoEditQO socialuniMineUserDetailRO);

    @PostMapping("switchOpenUserContactInfo")
    @Operation(summary = "用户切换他人是否可获取联系方式功能")
    ResultRO<SocialuniUserExpandDetailEditRO> switchOpenUserContactInfo(@RequestBody SocialUserContactInfoEditQO socialuniMineUserDetailRO);

    @PostMapping("queryExtendFriendUsers")
    @Operation(summary = "获取扩列用户列表")
    ResultRO<List<SocialuniUserExtendDetailRO>> queryExtendFriendUsers(@RequestBody @Valid SocialuniPageQueryQO<SocialuniUserExtendFriendQueryQO> socialuniPageQueryQO);

    @GetMapping("getUserContactInfo/{userId}")
    @Operation(summary = "获取用户的联系方式")
    ResultRO<String> getUserContactInfo(@PathVariable("userId") String userId);

    @GetMapping("getMineUserExpandDetail")
    @Operation(summary = "获取扩展信息")
    ResultRO<SocialuniUserExpandDetailEditRO> getMineUserExpandDetail();

}