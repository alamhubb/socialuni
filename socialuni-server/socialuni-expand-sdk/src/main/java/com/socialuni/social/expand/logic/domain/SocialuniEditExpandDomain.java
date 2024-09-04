package com.socialuni.social.expand.logic.domain;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.expand.logic.check.SocialuniSchoolCheck;
import com.socialuni.social.expand.model.SocialuniUserExpandDetailEditRO;
import com.socialuni.social.expand.utils.SocialuniUserExpandDOUtil;
import com.socialuni.social.expand.utils.SocialuniUserExpandUtil;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserExtendFriendLogDOUtil;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.user.sdk.model.QO.SocialUserContactInfoEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserSchoolNameEditQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.expand.dao.DO.SocialuniUserExpandDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniEditExpandDomain {
    @Resource
    SocialuniSchoolCheck socialuniSchoolCheck;

    public SocialuniUserExpandDetailEditRO editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        String schoolName = socialMineUserDetailQO.getSchoolName();

        socialuniSchoolCheck.checkSchoolName(schoolName);

        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDo SocialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        SocialuniUserExpandDo.setSchoolName(schoolName);

        SocialuniUserExpandDOUtil.saveUserExpandDO(SocialuniUserExpandDo);

        return SocialuniUserExpandUtil.getMineUserExpandDetail();
    }


    public SocialuniUserExpandDetailEditRO editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {
        String contactInfo = socialMineUserDetailQO.getContactInfo();

        if (StringUtils.isEmpty(contactInfo)) {
            throw new SocialParamsException("联系方式不能为空");
        }

        if (!SocialuniTextContentUtil.hasContactInfo(contactInfo)) {
            throw new SocialParamsException("错误的联系方式格式，请确认后重新输入");
        }

        //前端设置，是否开启联系方式，点击开启或者不开启，然后向后台传递参数，所以不需要后台做逻辑，是否开启

        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDo SocialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(mineUserId);

        SocialuniUserExpandDo.setContactInfo(contactInfo);
        SocialuniUserExpandDo.setOpenContactInfo(socialMineUserDetailQO.getOpenContactInfo());

        SocialuniUserExpandDOUtil.saveUserExpandDO(SocialuniUserExpandDo);

        //生成用户扩列记录
        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();

//        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserId);

//        return socialuniMineUserDetailRO;
        return SocialuniUserExpandUtil.getMineUserExpandDetail();
    }

}
