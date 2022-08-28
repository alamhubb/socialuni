package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("socialuni/notify")
@FeignClient(name = "notify", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniNotifyAPI {
    @PostMapping("queryNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryNotifies();

    @PostMapping("queryUnreadNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies();

    @PostMapping("queryUnreadNotifiesAndUpdateHasRead")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead();
}
