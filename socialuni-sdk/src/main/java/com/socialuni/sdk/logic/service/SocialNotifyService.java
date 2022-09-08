package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.logic.domain.notify.SocialQueryNotifyDomain;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
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
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifiesAndUpdateHasRead(mineUser);
        return new ResultRO<>(list);
    }
}
