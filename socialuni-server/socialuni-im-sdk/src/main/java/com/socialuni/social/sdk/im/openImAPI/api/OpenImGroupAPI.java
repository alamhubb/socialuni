package com.socialuni.social.sdk.im.openImAPI.api;

import com.socialuni.social.sdk.im.openImAPI.model.OpenImResultRO;
import com.socialuni.social.sdk.im.openImAPI.model.group.OpenImJoinGroupQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Tag(name = "openIm模块/群组模块")
@FeignClient(name = "imGroup", url = "${socialuni.open-im-server-url:https://im.socialuni.cn:50002}", path = "group")
public interface OpenImGroupAPI {
    @PostMapping("join_group")
    @Operation(summary = "用于加入群组")
    OpenImResultRO JoinGroup(@RequestBody @Valid OpenImJoinGroupQO joinGroupQO);
}

