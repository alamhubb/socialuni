package com.socialuni.social.sdk.feignAPI.im;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.social.sdk.model.QO.user.SocialuniUserImgDeleteQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserEditQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserImgRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


//@RequestMapping("socialuni/user")
@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "imUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/im/user")
public interface SocialuniImUserAPI {
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