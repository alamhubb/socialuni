package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.entity.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserExpandRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserExpandDOUtil {

    static SocialuniUserExpandRepository socialuniUserExpandApi;

    @Resource
    public void setSocialuniUserExpandRepository(SocialuniUserExpandRepository socialuniUserExpandApi) {
        SocialuniUserExpandDOUtil.socialuniUserExpandApi = socialuniUserExpandApi;
    }

    public static SocialuniUserExpandDo getAllowNull(Integer userId) {
        SocialuniUserExpandDo SocialuniUserExpandDo = socialuniUserExpandApi.findByUserId(userId);
        return SocialuniUserExpandDo;
    }

    public static SocialuniUserExpandDo getOrCreate(Integer userId) {
        SocialuniUserExpandDo SocialuniUserExpandDo = getAllowNull(userId);
        if (SocialuniUserExpandDo == null) {
            SocialuniUserExpandDo = new SocialuniUserExpandDo();
            SocialuniUserExpandDo.setUserId(userId);
            SocialuniUserExpandDo.setOpenContactInfo(false);
        }
        return SocialuniUserExpandDo;
    }

    public static String getUserSchoolNameNotNull(Integer userId) {
        SocialuniUserExpandDo SocialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(userId);
        if (StringUtils.isEmpty(SocialuniUserExpandDo.getSchoolName())) {
            throw new SocialParamsException("请设置校园名称");
        }
        return SocialuniUserExpandDo.getSchoolName();
    }

    public static SocialuniUserExpandDo saveUserExpandDO(SocialuniUserExpandDo SocialuniUserExpandDo) {
        return socialuniUserExpandApi.savePut(SocialuniUserExpandDo);
    }



}
