package com.socialuni.social.userImg.api;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.userImg.model.SocialuniUserImgDeleteQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "userImg", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/userImg")
public interface SocialuniUserImgAPI {
    @PostMapping("addUserImg")
    @Operation(summary = "添加用户图片")
    ResultRO<List<SocialuniUserImgRO>> addUserImg(@RequestBody @Valid SocialuniImgAddQO socialUserImgAddQO);

    @PostMapping("deleteUserImg")
    @Operation(summary = "删除用户图片")
    ResultRO<List<SocialuniUserImgRO>> deleteUserImg(@RequestBody @Valid SocialuniUserImgDeleteQO centerUserImgDeleteQO);

    @GetMapping("getUserImgList/{userId}")
    @Operation(summary = "获取用户图片列表")
    ResultRO<List<SocialuniUserImgRO>> getUserImgList(@PathVariable("userId") String userId);


    @GetMapping("getMineUserImgList")
    @Operation(summary = "获取用户图片列表")
    ResultRO<List<SocialuniUserImgRO>> getMineUserImgList();
}