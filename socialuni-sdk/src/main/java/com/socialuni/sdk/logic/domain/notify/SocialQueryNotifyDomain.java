package com.socialuni.sdk.logic.domain.notify;

import com.socialuni.sdk.constant.NotifyType;
import com.socialuni.sdk.logic.factory.SocialUnreadNotifyVOFactory;
import com.socialuni.sdk.dao.DO.NotifyDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.sdk.dao.repository.NotifyRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialQueryNotifyDomain {
    @Resource
    private NotifyRepository notifyRepository;

    public List<SocialUnreadNotifyVO> queryNotifies(SocialuniUserDO mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findTop20ByReceiveUserIdAndTypeInOrderByHasReadDescIdDesc(mineUser.getUnionId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }

    public List<SocialUnreadNotifyVO> queryUnreadNotifies(SocialuniUserDO mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findAllByReceiveUserIdAndTypeInAndHasReadFalseOrderByIdDesc(mineUser.getUnionId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }

    public List<SocialUnreadNotifyVO> queryUnreadNotifiesAndUpdateHasRead(SocialuniUserDO mineUser) {
        List<NotifyDO> notifyDOS = notifyRepository.findAllByReceiveUserIdAndTypeInAndHasReadFalseOrderByIdDesc(mineUser.getUnionId(), NotifyType.comments);
        for (NotifyDO notifyDO : notifyDOS) {
            notifyDO.setHasRead(true);
        }
        notifyRepository.saveAll(notifyDOS);
        notifyDOS = notifyRepository.findTop20ByReceiveUserIdAndTypeInOrderByHasReadDescIdDesc(mineUser.getUnionId(), NotifyType.comments);
        return SocialUnreadNotifyVOFactory.unreadNotifyDOToVOS(notifyDOS);
    }
}