package com.socialuni.sdk.utils.model.DO;

import com.socialuni.sdk.dao.DO.user.*;
import com.socialuni.sdk.dao.repository.SocialuniUserExpandRepository;

import com.socialuni.social.web.sdk.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserExpandDOUtil {

    static SocialuniUserExpandRepository socialuniUserExpandRepository;

    @Resource
    public void setSocialuniUserExpandRepository(SocialuniUserExpandRepository socialuniUserExpandRepository) {
        SocialuniUserExpandDOUtil.socialuniUserExpandRepository = socialuniUserExpandRepository;
    }


    public static SocialuniUserExpandDO getUserExpandDO(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = socialuniUserExpandRepository.findByUserId(userId);
        return socialuniUserExpandDO;
    }

    public static String getUserSchoolNameAllowNull(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = SocialuniUserExpandDOUtil.getUserExpandDO(userId);
        if (socialuniUserExpandDO == null) {
            return null;
        }
        return socialuniUserExpandDO.getSchoolName();
    }
    public static String getUserSchoolNameNotNull(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = SocialuniUserExpandDOUtil.getUserExpandDO(userId);
        if (socialuniUserExpandDO == null) {
            throw new SocialParamsException("请设置校园名称");
        }
        return socialuniUserExpandDO.getSchoolName();
    }

}
