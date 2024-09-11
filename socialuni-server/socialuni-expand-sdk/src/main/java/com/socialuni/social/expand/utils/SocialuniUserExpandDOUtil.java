package com.socialuni.social.expand.utils;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.expand.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.expand.dao.repository.SocialuniUserExpandRepository;
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

    public static SocialuniUserExpandDo getAllowNull(Long userId) {
        SocialuniUserExpandDo SocialuniUserExpandDo = socialuniUserExpandApi.findByUserId(userId);
        return SocialuniUserExpandDo;
    }

    public static SocialuniUserExpandDo getNotNull(Long userId) {
        SocialuniUserExpandDo SocialuniUserExpandDo = getAllowNull(userId);
        if (SocialuniUserExpandDo == null) {
            throw new SocialParamsException("未给用户扩展表数据");
        }
        return SocialuniUserExpandDo;
    }

    public static SocialuniUserExpandDo getOrCreate(Long userId) {
        SocialuniUserExpandDo socialuniUserExpandDo = getAllowNull(userId);
        if (socialuniUserExpandDo == null) {
            socialuniUserExpandDo = new SocialuniUserExpandDo();
            socialuniUserExpandDo.setUserId(userId);
            socialuniUserExpandDo.setOpenContactInfo(false);
            socialuniUserExpandDo.setLastOnlineTime(new Date());
            log.info("创建 用户记录：{}", userId);
            socialuniUserExpandDo = saveUserExpandDO(socialuniUserExpandDo);
        }
        return socialuniUserExpandDo;
    }

    public static String getUserSchoolNameNotNull(Long userId) {
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
