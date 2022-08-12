package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("notify")
public interface SocialuniNotifyAPI {
    @PostMapping("queryNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryNotifies();

    @PostMapping("queryUnreadNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies();

    @PostMapping("queryUnreadNotifiesAndUpdateHasRead")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead();
}
