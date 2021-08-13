package com.socialuni.social.sdk.repository;

import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserPhoneRepository extends JpaRepository<SocialUserPhoneDO, Integer> {
    SocialUserPhoneDO findByPhoneNumAndStatus(String phoneNum, String status);
    SocialUserPhoneDO findByUserIdAndStatus(Integer userId, String status);
}