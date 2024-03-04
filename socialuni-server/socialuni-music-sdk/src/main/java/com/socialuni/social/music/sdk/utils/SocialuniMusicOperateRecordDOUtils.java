package com.socialuni.social.music.sdk.utils;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.constant.SocialuniCommmonRoleType;
import com.socialuni.social.common.sdk.constant.SocialuniSysRoleId;
import com.socialuni.social.common.sdk.dao.DO.SocialuniAppOperateRecordDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.enumeration.SocialuniChatOperateType;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class SocialuniMusicOperateRecordDOUtils {
    public static SocialuniAppOperateRecordDO createMusicRecord(Integer contentId, String type, Integer userId, String remark, String roleId) {
        SocialuniAppOperateRecordDO socialuniAppOperateRecordDO = new SocialuniAppOperateRecordDO();

        socialuniAppOperateRecordDO.setContentType(SocialuniContentType.chat);
        socialuniAppOperateRecordDO.setContentId(contentId);
        socialuniAppOperateRecordDO.setType(type);
        socialuniAppOperateRecordDO.setRemark(remark);
        socialuniAppOperateRecordDO.setUserId(userId);
        socialuniAppOperateRecordDO.setRoleId(roleId);
        socialuniAppOperateRecordDO.setRoleType(SocialuniCommmonRoleType.chatGroupManage);

        socialuniAppOperateRecordDO = SocialuniRepositoryFacade.save(socialuniAppOperateRecordDO);

        return socialuniAppOperateRecordDO;

    }
}
