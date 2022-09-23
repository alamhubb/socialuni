package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserExpandDO;
import com.socialuni.sdk.dao.repository.user.SocialuniUserExpandRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.repository.community.SocialuniTagTypeRepository;
import com.socialuni.sdk.dao.store.SocialuniCircleRedis;
import com.socialuni.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.sdk.logic.check.SocialuniSchoolCheck;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniEditExpandDomain {
    @Resource
    UserRepository userRepository;

    @Resource
    SocialCircleRepository socialCircleRepository;
    @Resource
    SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    SocialuniCircleRedis socialCircleRedis;
    @Resource
    SocialuniSchoolCheck socialuniSchoolCheck;
    @Resource
    SocialuniUserExpandRepository socialuniUserExpandRepository;

    public SocialuniMineUserDetailRO editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        String schoolName = socialMineUserDetailQO.getSchoolName();

        socialuniSchoolCheck.checkSchoolName(schoolName);

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDO socialuniUserExpandDO = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        socialuniUserExpandDO.setSchoolName(schoolName);

        SocialuniUserExpandDOUtil.saveUserExpandDO(socialuniUserExpandDO);

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

        SocialuniUserExpandDO socialuniUserExpandDO = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        socialuniUserExpandDO.setContactInfo(contactInfo);
        socialuniUserExpandDO.setOpenContactInfo(socialMineUserDetailQO.getOpenContactInfo());

        SocialuniUserExpandDOUtil.saveUserExpandDO(socialuniUserExpandDO);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserId);

        return socialuniMineUserDetailRO;
    }

}
