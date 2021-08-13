package com.socialuni.api.feignAPI;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("notify")
@FeignClient(name = "notify", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniNotifyAPI {
    @PostMapping("queryNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryNotifies();

    @PostMapping("queryUnreadNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies();

    @PostMapping("queryUnreadNotifiesAndUpdateHasRead")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead();
}
