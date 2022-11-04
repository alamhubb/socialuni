package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.logic.check.SocialuniSchoolCheck;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.entity.SocialuniUserExpandDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniEditExpandDomain {
    @Resource
    SocialuniSchoolCheck socialuniSchoolCheck;

    public SocialuniMineUserDetailRO editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        String schoolName = socialMineUserDetailQO.getSchoolName();

        socialuniSchoolCheck.checkSchoolName(schoolName);

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDo SocialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        SocialuniUserExpandDo.setSchoolName(schoolName);

        SocialuniUserExpandDOUtil.saveUserExpandDO(SocialuniUserExpandDo);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserId);

        return socialuniMineUserDetailRO;
    }


    public SocialuniMineUserDetailRO editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {
        String contactInfo = socialMineUserDetailQO.getContactInfo();

        if (StringUtils.isEmpty(contactInfo)) {
            throw new SocialParamsException("联系方式不能为空");
        }

        if (!SocialuniTextContentUtil.hasContactInfo(contactInfo)) {
            throw new SocialParamsException("错误的联系方式格式，请确认后重新输入");
        }

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDo SocialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        SocialuniUserExpandDo.setContactInfo(contactInfo);
        SocialuniUserExpandDo.setOpenContactInfo(socialMineUserDetailQO.getOpenContactInfo());

        SocialuniUserExpandDOUtil.saveUserExpandDO(SocialuniUserExpandDo);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserId);

        return socialuniMineUserDetailRO;
    }

}
