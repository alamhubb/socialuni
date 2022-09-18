package com.socialuni.web.controller;

import com.socialuni.sdk.feignAPI.community.SocialuniNotifyAPI;
import com.socialuni.sdk.logic.service.SocialuniNotifyService;
import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialuniNotifyController implements SocialuniNotifyAPI {
    @Resource
    private SocialuniNotifyService centerNotifyService;

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
