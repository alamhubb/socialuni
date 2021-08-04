package com.socialuni.sdk.domain.notify;

import com.socialuni.social.model.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.sdk.constant.NotifyType;
import com.socialuni.sdk.factory.SocialUnreadNotifyVOFactory;
import com.socialuni.sdk.model.DO.NotifyDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.NotifyRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialQueryNotifyDomain {
    @Resource
    private NotifyRepository notifyRepository;

    public List<SocialUnreadNotifyVO> queryNotifies(UserDO mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findTop20ByReceiveUserIdAndTypeInOrderByHasReadDescIdDesc(mineUser.getId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }

    public List<SocialUnreadNotifyVO> queryUnreadNotifies(UserDO mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findAllByReceiveUserIdAndTypeInAndHasReadFalseOrderByIdDesc(mineUser.getId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }

    public List<SocialUnreadNotifyVO> queryUnreadNotifiesAndUpdateHasRead(UserDO mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findAllByReceiveUserIdAndTypeInAndHasReadFalseOrderByIdDesc(mineUser.getId(), NotifyType.comments);
        for (NotifyDO notifyDO : notifyDOS) {
            notifyDO.setHasRead(true);
        }
        notifyRepository.saveAll(notifyDOS);
        notifyDOS = notifyRepository.findTop20ByReceiveUserIdAndTypeInOrderByHasReadDescIdDesc(mineUser.getId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }
}
