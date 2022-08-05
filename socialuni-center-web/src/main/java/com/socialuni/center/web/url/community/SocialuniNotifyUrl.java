package com.socialuni.center.web.url.community;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.app.SocialUnreadNotifyVO;
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
