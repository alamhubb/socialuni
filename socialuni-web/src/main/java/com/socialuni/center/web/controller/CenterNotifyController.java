package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniNotifyAPI;
import com.socialuni.center.web.serive.CenterNotifyService;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterNotifyController implements SocialuniNotifyAPI {
    @Resource
    private CenterNotifyService centerNotifyService;

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        return centerNotifyService.queryNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        return centerNotifyService.queryUnreadNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        return centerNotifyService.queryUnreadNotifiesAndUpdateHasRead();
    }
}
