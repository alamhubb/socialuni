package com.socialuni.social.sdk.url.community;

import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("notify")
public interface SocialuniNotifyUrl {
    @PostMapping("queryNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryNotifies();

    @PostMapping("queryUnreadNotifies")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies();

    @PostMapping("queryUnreadNotifiesAndUpdateHasRead")
    ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead();
}
