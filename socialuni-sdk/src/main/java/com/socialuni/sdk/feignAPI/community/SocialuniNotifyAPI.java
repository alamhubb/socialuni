package com.socialuni.sdk.feignAPI.community;

import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("socialuni/notify")
@FeignClient(name = "notify", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "社区模块/通知模块")
public interface SocialuniNotifyAPI {
    @GetMapping("queryNotifies")
    @Operation(summary = "查询所有通知列表")
    ResultRO<List<SocialUnreadNotifyVO>> queryNotifies();

    @GetMapping("queryUnreadNotifies")
    @Operation(summary = "查询未读通知列表")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies();

    @GetMapping("queryUnreadNotifiesAndUpdateHasRead")
    @Operation(summary = "查询未读通知列表并更新为已读")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead();
}
