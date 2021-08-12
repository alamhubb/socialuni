package com.socialuni.sdk.store;

import com.socialuni.constant.CommonStatus;
import com.socialuni.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.repository.SocialUserPhoneRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserPhoneStore {
    @Resource
    SocialUserPhoneRepository socialUserPhoneRepository;

    public SocialUserPhoneDO findByPhoneNum(String phoneNum) {
        return socialUserPhoneRepository.findByPhoneNumAndStatus(phoneNum, CommonStatus.enable);
    }

    public SocialUserPhoneDO findByUserId(Integer userId) {
        return socialUserPhoneRepository.findByUserIdAndStatus(userId, CommonStatus.enable);
    }
}