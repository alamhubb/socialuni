package com.socialuni.social.im.openImAPI.api;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.im.openImAPI.model.OpenImResultRO;
import com.socialuni.social.im.openImAPI.model.group.OpenImJoinGroupQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Tag(name = "openIm模块/群组模块")
@FeignClient(name = "openImGroup", url = "${socialuni.open-im-server-url:https://im.socialuni.cn}", path = "group")
public interface OpenImGroupAPI {
    @PostMapping("join_group")
    @Operation(summary = "删除用户图片")
    OpenImResultRO deleteUserImg(@RequestBody @Valid OpenImJoinGroupQO joinGroupQO);
}

