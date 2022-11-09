package com.socialuni.social.sdk.feignAPI.user;

import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.RO.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.RO.SocialuniUserDetailRO;
import com.socialuni.social.user.sdk.model.RO.SocialuniUserImgRO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


//@RequestMapping("socialuni/user")
@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "user", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/user")
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