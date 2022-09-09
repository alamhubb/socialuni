package com.socialuni.sdk.utils;

import com.socialuni.sdk.dao.DO.user.*;
import com.socialuni.sdk.dao.repository.SocialuniUserExpandRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserExpandUtil {

    static SocialuniUserExpandRepository socialuniUserExpandRepository;

    @Resource
    public void setSocialuniUserExpandRepository(SocialuniUserExpandRepository socialuniUserExpandRepository) {
        SocialuniUserExpandUtil.socialuniUserExpandRepository = socialuniUserExpandRepository;
    }


    public static SocialuniUserExpandDO getUserExpandDO(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = socialuniUserExpandRepository.findByUserId(userId);
        return socialuniUserExpandDO;
    }

    public static String getUserSchoolName(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = socialuniUserExpandRepository.findByUserId(userId);
        if (socialuniUserExpandDO == null) {
            return null;
        }
        return socialuniUserExpandDO.getSchoolName();
    }
}
