package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.user.sdk.model.SocialuniUserExpandDO;
import com.socialuni.social.user.sdk.api.SocialuniUserExpandRepository;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public static SocialuniUserExpandDO getAllowNull(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = socialuniUserExpandRepository.findByUserId(userId);
        return socialuniUserExpandDO;
    }

    public static SocialuniUserExpandDO getOrCreate(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = getAllowNull(userId);
        if (socialuniUserExpandDO == null) {
            socialuniUserExpandDO = new SocialuniUserExpandDO();
            socialuniUserExpandDO.setUserId(userId);
            socialuniUserExpandDO.setOpenContactInfo(false);
        }
        return socialuniUserExpandDO;
    }

    public static String getUserSchoolNameNotNull(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = SocialuniUserExpandDOUtil.getOrCreate(userId);
        if (StringUtils.isEmpty(socialuniUserExpandDO.getSchoolName())) {
            throw new SocialParamsException("请设置校园名称");
        }
        return socialuniUserExpandDO.getSchoolName();
    }

    public static SocialuniUserExpandDO saveUserExpandDO(SocialuniUserExpandDO socialuniUserExpandDO) {
        return socialuniUserExpandRepository.save(socialuniUserExpandDO);
    }



}
