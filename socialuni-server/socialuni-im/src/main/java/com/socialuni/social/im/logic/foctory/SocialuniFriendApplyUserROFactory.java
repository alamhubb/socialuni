package com.socialuni.social.im.logic.foctory;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.im.enumeration.SocialuniAddFriendStatus;
import com.socialuni.social.im.model.RO.SocialuniFriendApplyUserRO;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class SocialuniFriendApplyUserROFactory {

    public static SocialuniFriendApplyUserRO getSocialuniFriendApplyUserRO(SocialuniFriendApplyRecordDO socialuniFriendApplyRecordDO) {

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialuniUserDo user = SocialuniUserUtil.getUserNotNull(socialuniFriendApplyRecordDO.getUserId());


        SocialuniUserRO socialuniUserRO = SocialuniUserROFactory.getUserRO(user, mineUser);
        SocialuniFriendApplyUserRO socialuniFriendApplyUserRO = new SocialuniFriendApplyUserRO(socialuniUserRO);

        socialuniFriendApplyUserRO.setApplyMsg(socialuniFriendApplyRecordDO.getApplyMsg());
        socialuniFriendApplyUserRO.setFriendApplyStatus(socialuniFriendApplyRecordDO.getStatus());
        socialuniFriendApplyUserRO.setCreateTime(socialuniFriendApplyRecordDO.getCreateTime());
        return socialuniFriendApplyUserRO;
    }

}
