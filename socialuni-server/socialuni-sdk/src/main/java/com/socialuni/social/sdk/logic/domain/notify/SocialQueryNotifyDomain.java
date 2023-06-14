package com.socialuni.social.sdk.logic.domain.notify;

import com.socialuni.social.im.enumeration.NotifyType;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.common.sdk.dao.repository.NotifyRepository;
import com.socialuni.social.sdk.logic.factory.SocialUnreadNotifyVOFactory;
import com.socialuni.social.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialQueryNotifyDomain {
    @Resource
    private NotifyRepository notifyRepository;

    public List<SocialUnreadNotifyVO> queryNotifies(SocialuniUserDo mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findTop20ByBeUserIdAndTypeInOrderByHasReadDescIdDesc(mineUser.getUnionId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }

    public List<SocialUnreadNotifyVO> queryUnreadNotifies(SocialuniUserDo mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findAllByBeUserIdAndTypeInAndHasReadFalseOrderByIdDesc(mineUser.getUnionId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }

    public List<SocialUnreadNotifyVO> queryUnreadNotifiesAndUpdateHasRead(SocialuniUserDo mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findAllByBeUserIdAndTypeInAndHasReadFalseOrderByIdDesc(mineUser.getUnionId(), NotifyType.comments);
        for (NotifyDO notifyDO : notifyDOS) {
            notifyDO.setHasRead(true);
        }
        notifyRepository.saveAll(notifyDOS);
        notifyDOS = notifyRepository.findTop20ByBeUserIdAndTypeInOrderByHasReadDescIdDesc(mineUser.getUnionId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }
}
