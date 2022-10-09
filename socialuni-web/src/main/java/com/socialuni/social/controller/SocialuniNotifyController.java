package com.socialuni.social.controller;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.community.SocialuniNotifyAPI;
import com.socialuni.social.sdk.logic.service.SocialuniNotifyService;
import com.socialuni.social.sdk.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/notify")
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
