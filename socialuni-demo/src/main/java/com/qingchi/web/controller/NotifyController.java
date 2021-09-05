/*
package com.qingchi.web.controller;

import com.qingchi.web.service.NotifyService;
import com.socialuni.api.feignAPI.NotifyAPI;
import com.socialuni.api.model.RO.ResultRO;
import com.socialuni.api.model.RO.UnreadNotifyVO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class NotifyController implements NotifyAPI {
    @Resource
    private NotifyService notifyService;

    public ResultRO<List<UnreadNotifyVO>> queryNotifies() {
        return notifyService.queryNotifies();
    }

    public ResultRO<List<UnreadNotifyVO>> queryUnreadComments() {
        return notifyService.queryUnreadComments();
    }

    public ResultRO<List<UnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        return notifyService.queryUnreadNotifiesAndUpdateHasRead();
    }
}
*/
