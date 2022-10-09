package com.socialuni.social.controller;

import com.socialuni.social.sdk.dao.repository.dev.ThirdUserAuthRepository;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserRepository;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserTokenRepository;
import com.socialuni.social.sdk.logic.domain.phone.SocialBindUserSocialuniAccountDomain;
import com.socialuni.social.user.sdk.api.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class TestUnionIdService {
    @Resource
    UserApi userApi;
    @Resource
    ThirdUserRepository thirdUserRepository;
    @Resource
    ThirdUserAuthRepository thirdUserAuthRepository;
    @Resource
    ThirdUserTokenRepository thirdUserTokenRepository;
    @Resource
    SocialBindUserSocialuniAccountDomain socialBindUserSocialuniAccountDomain;

}
