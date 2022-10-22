package com.socialuni.social.sdk.feignAPI.community;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.RO.app.SocialUnreadNotifyVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@RequestMapping("socialuni/notify")
@FeignClient(name = "notify", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/notify")
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
