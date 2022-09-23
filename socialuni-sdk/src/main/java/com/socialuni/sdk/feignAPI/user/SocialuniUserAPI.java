package com.socialuni.sdk.feignAPI.user;

import com.socialuni.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.QO.user.edit.SocialUserEditQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserImgRO;
import com.socialuni.social.common.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("socialuni/user")
@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "user", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniUserAPI {
    @GetMapping("getMineUser")
    @Operation(summary = "获取用户个人详情")
    ResultRO<SocialuniMineUserDetailRO> getMineUser();

    @GetMapping("queryUserDetail/{userId}")
    @Operation(summary = "获取他人用户详情")
    ResultRO<SocialuniUserDetailRO> queryUserDetail(@PathVariable("userId") String userId);


    @PostMapping("editUser")
    @Operation(summary = "编辑用户")
    ResultRO<SocialuniMineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);

    @PostMapping("addUserImg")
    @Operation(summary = "添加用户图片")
    ResultRO<SocialuniMineUserDetailRO> addUserImg(@RequestBody @Valid SocialuniImgAddQO socialUserImgAddQO);

    @PostMapping("addUserAvatarImg")
    @Operation(summary = "添加用户图片")
    ResultRO<SocialuniMineUserDetailRO> addUserAvatarImg(@RequestBody @Valid SocialuniImgAddQO socialUserImgAddQO);

    @PostMapping("deleteUserImg")
    @Operation(summary = "删除用户图片")
    ResultRO<SocialuniMineUserDetailRO> deleteUserImg(@RequestBody @Valid SocialuniUserImgDeleteQO centerUserImgDeleteQO);

    @GetMapping("getUserImgList/{userId}")
    @Operation(summary = "获取用户图片列表")
    ResultRO<List<SocialuniUserImgRO>> getUserImgList(@PathVariable("userId") String userId);
}