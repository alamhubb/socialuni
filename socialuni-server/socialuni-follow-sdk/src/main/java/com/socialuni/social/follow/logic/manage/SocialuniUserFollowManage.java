package com.socialuni.social.follow.logic.manage;

import com.qingchi.qing.constant.SocialuniCommonStatus;
import com.socialuni.social.follow.dao.DO.SocialuniUserFollowDO;
import com.socialuni.social.follow.dao.repositroy.SocialuniFollowRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialuniUserFollowManage {
    @Resource
    private SocialuniFollowRepository followRepository;

    public SocialuniUserFollowDO createFollow(Long mineUserId, Long beUserId) {
        SocialuniUserFollowDO followDO = new SocialuniUserFollowDO();
        //两个用户粉丝和关注各加1
        followDO.setUserId(mineUserId);
        followDO.setBeUserId(beUserId);
        followDO = followRepository.save(followDO);
        return followDO;
    }

    public SocialuniUserFollowDO updateFollow(SocialuniUserFollowDO followDO, String status) {
        followDO.setStatus(status);
        followDO.setUpdateTime(new Date());
        followDO = followRepository.save(followDO);
        return followDO;
    }

    public boolean userHasFollowBeUser(Long userId, Long beUserId) {
        SocialuniUserFollowDO followDO = followRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
        return followDO != null && SocialuniCommonStatus.enable.equals(followDO.getStatus());
    }
}
