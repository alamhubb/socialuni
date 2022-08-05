package com.socialuni.center.web.serive;

import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.domain.notify.SocialQueryNotifyDomain;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CenterNotifyService {
    @Resource
    private SocialQueryNotifyDomain socialQueryNotifyDomain;

    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifies() {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        List<SocialUnreadNotifyVO> list = socialQueryNotifyDomain.queryUnreadNotifiesAndUpdateHasRead(mineUser);
        return new ResultRO<>(list);
    }
}
