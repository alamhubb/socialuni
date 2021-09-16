package com.socialuni.demo.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniNotifyAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class NotifyController implements SocialuniNotifyAPI {
    @Resource
    private SocialuniNotifyAPI socialuniNotifyAPI;

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        return socialuniNotifyAPI.queryNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        return socialuniNotifyAPI.queryUnreadNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        return socialuniNotifyAPI.queryUnreadNotifiesAndUpdateHasRead();
    }
}
