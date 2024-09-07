package com.socialuni.social.user.sdk.api.user;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.dev.api.SocialuniNoUseFeignAspect;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


//@RequestMapping("socialuni/user")
@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "mineUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/mineUser")
public interface SocialuniMineUserAPI {
    @GetMapping("getMineUser")
    @Operation(summary = "获取用户个人详情")
    ResultRO<SocialuniUserRO> getMineUser();

    @GetMapping("refreshToken")
    @Operation(summary = "刷新token")
    ResultRO<SocialLoginRO<SocialuniUserRO>> refreshToken();

    @PostMapping("editUser")
    @Operation(summary = "编辑用户")
    ResultRO<SocialuniUserRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);

    @GetMapping("randomUserAvatar")
    @Operation(summary = "添加用户图片")
    ResultRO<SocialuniUserRO> randomUserAvatar();

    @PostMapping("addUserAvatarImg")
    @Operation(summary = "添加用户图片")
    ResultRO<SocialuniUserRO> addUserAvatarImg(@RequestBody @Valid SocialuniImgAddQO socialUserImgAddQO);
}