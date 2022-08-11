package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniNotifyAPI;
import com.socialuni.sdk.serive.CenterNotifyService;
import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.api.model.ResultRO;
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
