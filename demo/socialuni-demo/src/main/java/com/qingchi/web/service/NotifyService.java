/*
package com.qingchi.web.service;

import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotifyService {
    @Resource
    private CenterQueryNotifyDomain centerQueryNotifyDomain;

    public ResultRO<List<SocialUnreadNotifyVO>> queryNotifies() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialUnreadNotifyVO> list = centerQueryNotifyDomain.queryNotifies(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadComments() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialUnreadNotifyVO> list = centerQueryNotifyDomain.queryUnreadComments(mineUser);
        return new ResultRO<>(list);
    }

    public ResultRO<List<SocialUnreadNotifyVO>> queryUnreadNotifiesAndUpdateHasRead() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialUnreadNotifyVO> list = centerQueryNotifyDomain.queryUnreadNotifiesAndUpdateHasRead(mineUser);
        return new ResultRO<>(list);
    }
}
*/
