package com.qingchi.web.manage;

import com.qingchi.web.model.DO.UserDetailDO;
import com.qingchi.web.repsotiory.UserDetailRepository;
import com.socialuni.social.entity.model.DO.user.UserDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserDetailManage {
    @Resource
    UserDetailRepository userDetailRepository;

    public UserDetailDO getOrCreateUserDetail(UserDO mineUser) {
        UserDetailDO userDetailDO = userDetailRepository.findOneByUserId(mineUser.getId());
        if (userDetailDO != null) {
            return userDetailDO;
        }
        userDetailDO = new UserDetailDO(mineUser);
        userDetailDO = userDetailRepository.save(userDetailDO);
        return userDetailDO;
    }

    public void updateUserOnlineFlag(String userId, boolean onlineFlag) {
        UserDetailDO user = userDetailRepository.findOneByUserId(Integer.valueOf(userId));
        if (user != null) {
            user.setOnlineFlag(onlineFlag);
            user.setLastOnlineTime(new Date());
            userDetailRepository.save(user);
        }
    }
}
