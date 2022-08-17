package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.sdk.serive.CenterNotifyService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterNotifyController  {
    @Resource
    private CenterNotifyService centerNotifyService;

    @PostMapping("queryNotifies")
    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        return centerNotifyService.queryNotifies();
    }

    @PostMapping("queryUnreadNotifies")
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies(){
        return centerNotifyService.queryUnreadNotifies();
    }

    @PostMapping("queryUnreadNotifiesAndUpdateHasRead")
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        return centerNotifyService.queryUnreadNotifiesAndUpdateHasRead();
    }
}
