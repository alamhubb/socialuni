package com.socialuni.sdk.dao.utils;

import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
import com.socialuni.sdk.dao.repository.user.SocialuniUserInfoRepository;
import com.socialuni.sdk.dao.repository.user.SocialuniUserSocialCoinRepository;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class SocialuniUserInfoDOUtil<T extends SocialUnionContentBaseDO> {
    @Resource
    private SocialuniUserInfoRepository<T> socialuniUserInfoRepository;

    public List<T> findAll() {
        return socialuniUserInfoRepository.findAll();
    }
}
