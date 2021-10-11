package com.socialuni.demo.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniNotifyAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.sdk.utils.model.SocialUserAccountUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NotifyController implements SocialuniNotifyAPI {
    @Resource
    private SocialuniNotifyAPI socialuniNotifyAPI;

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        if (!SocialUserAccountUtil.mineBindSocialuniAccount()) {
            return ResultRO.success(new ArrayList<>());
        }
        return socialuniNotifyAPI.queryNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        if (!SocialUserAccountUtil.mineBindSocialuniAccount()) {
            return ResultRO.success(new ArrayList<>());
        }
        return socialuniNotifyAPI.queryUnreadNotifies();
    }

    @Override
    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        if (!SocialUserAccountUtil.mineBindSocialuniAccount()) {
            return ResultRO.success(new ArrayList<>());
        }
        return socialuniNotifyAPI.queryUnreadNotifiesAndUpdateHasRead();
    }
}
