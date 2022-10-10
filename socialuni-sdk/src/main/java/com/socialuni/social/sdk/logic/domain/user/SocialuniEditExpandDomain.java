package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.user.sdk.api.UserApi;
import com.socialuni.social.user.sdk.model.SocialuniUserExpandModel;
import com.socialuni.social.user.sdk.api.SocialuniUserExpandApi;
import com.socialuni.social.community.sdk.api.SocialCircleApi;
import com.socialuni.social.community.sdk.api.SocialuniTagTypeApi;
import com.socialuni.social.sdk.dao.store.SocialuniCircleRedis;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.logic.check.SocialuniSchoolCheck;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniEditExpandDomain {
    @Resource
    UserApi userApi;

    @Resource
    SocialCircleApi socialCircleApi;
    @Resource
    SocialuniTagTypeApi tagTypeRepository;
    @Resource
    SocialuniCircleRedis socialCircleRedis;
    @Resource
    SocialuniSchoolCheck socialuniSchoolCheck;
    @Resource
    SocialuniUserExpandApi socialuniUserExpandApi;

    public SocialuniMineUserDetailRO editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        String schoolName = socialMineUserDetailQO.getSchoolName();

        socialuniSchoolCheck.checkSchoolName(schoolName);

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandModel socialuniUserExpandModel = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        socialuniUserExpandModel.setSchoolName(schoolName);

        SocialuniUserExpandDOUtil.saveUserExpandDO(socialuniUserExpandModel);

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

        SocialuniUserExpandModel socialuniUserExpandModel = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        socialuniUserExpandModel.setContactInfo(contactInfo);
        socialuniUserExpandModel.setOpenContactInfo(socialMineUserDetailQO.getOpenContactInfo());

        SocialuniUserExpandDOUtil.saveUserExpandDO(socialuniUserExpandModel);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserId);

        return socialuniMineUserDetailRO;
    }

}
