package com.socialuni.social.sdk.dao.utils.user;

import com.socialuni.social.user.sdk.model.SocialuniUserExpandModel;
import com.socialuni.social.user.sdk.api.SocialuniUserExpandApi;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserExpandDOUtil {

    static SocialuniUserExpandApi socialuniUserExpandApi;

    @Resource
    public void setSocialuniUserExpandRepository(SocialuniUserExpandApi socialuniUserExpandApi) {
        SocialuniUserExpandDOUtil.socialuniUserExpandApi = socialuniUserExpandApi;
    }

    public static SocialuniUserExpandModel getAllowNull(Integer userId) {
        SocialuniUserExpandModel socialuniUserExpandModel = socialuniUserExpandApi.findByUserId(userId);
        return socialuniUserExpandModel;
    }

    public static SocialuniUserExpandModel getOrCreate(Integer userId) {
        SocialuniUserExpandModel socialuniUserExpandModel = getAllowNull(userId);
        if (socialuniUserExpandModel == null) {
            socialuniUserExpandModel = new SocialuniUserExpandModel();
            socialuniUserExpandModel.setUserId(userId);
            socialuniUserExpandModel.setOpenContactInfo(false);
        }
        return socialuniUserExpandModel;
    }

    public static String getUserSchoolNameNotNull(Integer userId) {
        SocialuniUserExpandModel socialuniUserExpandModel = SocialuniUserExpandDOUtil.getOrCreate(userId);
        if (StringUtils.isEmpty(socialuniUserExpandModel.getSchoolName())) {
            throw new SocialParamsException("请设置校园名称");
        }
        return socialuniUserExpandModel.getSchoolName();
    }

    public static SocialuniUserExpandModel saveUserExpandDO(SocialuniUserExpandModel socialuniUserExpandModel) {
        return socialuniUserExpandApi.savePut(socialuniUserExpandModel);
    }



}
