package com.socialuni.social.user.sdk.dao.utils;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserExpandRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialuniUserDOUtil {

    static SocialuniUserRepository socialuniUserRepository;

    @Resource
    public void setSocialuniUserRepository(SocialuniUserRepository socialuniUserRepository) {
        SocialuniUserDOUtil.socialuniUserRepository = socialuniUserRepository;
    }

    public SocialuniUserDo save(SocialuniUserDo socialuniUserDo) {
        return null;
    }
}
