package com.socialuni.social.sdk.feignAPI.user;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
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
@FeignClient(name = "mineUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/mineUser")
public interface SocialuniMineUserAPI {
    @GetMapping("getMineUser")
    @Operation(summary = "获取用户个人详情")
    ResultRO<SocialuniMineUserDetailRO> getMineUser();

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
}