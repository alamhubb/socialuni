package com.socialuni.social.sdk.dao;

import com.socialuni.constant.CommonStatus;
import com.socialuni.entity.model.DO.FollowDO;
import com.socialuni.social.sdk.repository.FollowRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
@Component
public class FollowDao {
    @Resource
    FollowRepository followRepository;

    public FollowDO findFirstByUserIdAndBeUserIdEnable(Integer userId, Integer beUserId) {
        return followRepository.findFirstByUserIdAndBeUserIdAndStatus(userId, beUserId, CommonStatus.enable);
    }

    public FollowDO findFirstByUserIdAndBeUserIdDelete(Integer userId, Integer beUserId) {
        return followRepository.findFirstByUserIdAndBeUserIdAndStatus(userId, beUserId, CommonStatus.delete);
    }

    public boolean userHasFollowBeUser(Integer userId, Integer beUserId) {
        Integer count = followRepository.countByUserIdAndBeUserIdAndStatus(userId, beUserId, CommonStatus.enable);
        return count > 0;
    }
}


