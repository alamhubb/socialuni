package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.logic.domain.notify.SocialQueryNotifyDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialNotifyService {
    @Resource
    private SocialQueryNotifyDomain socialQueryNotifyDomain;

    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifiesAndUpdateHasRead(mineUser);
        return new ResultRO<>(list);
    }
}
