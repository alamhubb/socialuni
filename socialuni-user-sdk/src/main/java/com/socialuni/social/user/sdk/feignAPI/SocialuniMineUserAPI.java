package com.socialuni.social.user.sdk.feignAPI;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("socialuni/mineUser")
@Tag(name = "用户模块/用户自身信息模块")
@FeignClient(name = "mineUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniMineUserAPI {
    @GetMapping("getMineUser")
    @Operation(summary = "获取用户个人详情")
    ResultRO<SocialuniUserRO> getMineUser();

    @PostMapping("editUser")
    @Operation(summary = "编辑用户")
    ResultRO<SocialuniUserRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);

    @PostMapping("addUserAvatarImg")
    @Operation(summary = "添加用户图片")
    ResultRO<SocialuniUserRO> addUserAvatarImg(@RequestBody @Valid SocialuniImgAddQO socialUserImgAddQO);
}