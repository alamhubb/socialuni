package com.socialuni.sdk.serive;

import com.socialuni.sdk.domain.notify.SocialQueryNotifyDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CenterNotifyService {
    @Resource
    private SocialQueryNotifyDomain socialQueryNotifyDomain;

    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifiesAndUpdateHasRead(mineUser);
        return new ResultRO<>(list);
    }
}
