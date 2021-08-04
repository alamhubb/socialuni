package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.notify.SocialQueryNotifyDomain;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/notify")
public class SocialNotifyService {
    @Resource
    private SocialQueryNotifyDomain socialQueryNotifyDomain;

    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifiesAndUpdateHasRead(mineUser);
        return new ResultRO<>(list);
    }
}
