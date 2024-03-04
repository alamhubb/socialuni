package com.socialuni.social.user.sdk.dao.utils;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserExpandRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

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

    public static SocialuniUserExpandDo getNotNull(Integer userId) {
        SocialuniUserExpandDo SocialuniUserExpandDo = getAllowNull(userId);
        if (SocialuniUserExpandDo == null) {
            throw new SocialParamsException("未给用户扩展表数据");
        }
        return SocialuniUserExpandDo;
    }

    public static SocialuniUserExpandDo getOrCreate(Integer userId) {
        SocialuniUserExpandDo socialuniUserExpandDo = getAllowNull(userId);
        if (socialuniUserExpandDo == null) {
            socialuniUserExpandDo = new SocialuniUserExpandDo();
            socialuniUserExpandDo.setUserId(userId);
            socialuniUserExpandDo.setOpenContactInfo(false);
            socialuniUserExpandDo.setLastOnlineTime(new Date());
        }
        return socialuniUserExpandDo;
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
