package com.socialuni.web.controller;

import com.socialuni.sdk.dao.repository.dev.ThirdUserAuthRepository;
import com.socialuni.sdk.dao.repository.dev.ThirdUserRepository;
import com.socialuni.sdk.dao.repository.dev.ThirdUserTokenRepository;
import com.socialuni.sdk.logic.domain.phone.SocialBindUserSocialuniAccountDomain;
import com.socialuni.sdk.dao.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class TestUnionIdService {
    @Resource
    UserRepository userRepository;
    @Resource
    ThirdUserRepository thirdUserRepository;
    @Resource
    ThirdUserAuthRepository thirdUserAuthRepository;
    @Resource
    ThirdUserTokenRepository thirdUserTokenRepository;
    @Resource
    SocialBindUserSocialuniAccountDomain socialBindUserSocialuniAccountDomain;

}
