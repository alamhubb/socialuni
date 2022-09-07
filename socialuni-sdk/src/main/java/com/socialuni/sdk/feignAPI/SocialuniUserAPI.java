package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("socialuni/user")
@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "user", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniUserAPI {
    @GetMapping("getMineUser")
    @Operation(summary = "获取用户个人详情")
    ResultRO<CenterMineUserDetailRO> getMineUser();

    @GetMapping("queryUserDetail/{talkId}")
    @Operation(summary = "获取他人用户详情")
    ResultRO<CenterUserDetailRO> queryUserDetail(@PathVariable("talkId") String userId);


    @PostMapping("editUser")
    @Operation(summary = "编辑用户")
    ResultRO<CenterMineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);

    @PostMapping("addUserImg")
    @Operation(summary = "添加用户图片")
    ResultRO<CenterMineUserDetailRO> addUserImg(@RequestBody @Valid SocialUserImgAddQO socialUserImgAddQO);

    @PostMapping("deleteUserImg")
    @Operation(summary = "删除用户图片")
    ResultRO<CenterMineUserDetailRO> deleteUserImg(@RequestBody @Valid CenterUserImgDeleteQO centerUserImgDeleteQO);
}