package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialNotifyService;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.sdk.url.community.SocialuniNotifyUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialuniNotifyController implements SocialuniNotifyUrl {
    @Resource
    private SocialNotifyService socialNotifyService;

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        return socialNotifyService.queryNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        return socialNotifyService.queryUnreadNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        return socialNotifyService.queryUnreadNotifiesAndUpdateHasRead();
    }
}
