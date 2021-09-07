package com.qingchi.web.dao;

import com.qingchi.web.constant.ExpenseType;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.entity.model.DO.chat.UserContactDO;
import com.socialuni.social.sdk.repository.UserContactRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserContactDao {
    @Resource
    private UserContactRepository userContactRepository;

    public UserContactDO findByUserIdAndBeUserId(Integer userId, Integer beUserId) {
        return userContactRepository.findOneByUserIdAndBeUserIdAndStatusAndType(
                userId, beUserId, CommonStatus.enable, ExpenseType.contact);
    }

}
