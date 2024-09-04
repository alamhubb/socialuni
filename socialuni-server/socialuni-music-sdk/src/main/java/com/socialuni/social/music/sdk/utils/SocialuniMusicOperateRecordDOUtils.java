package com.socialuni.social.music.sdk.utils;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.constant.SocialuniCommmonRoleType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniAppOperateRecordDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;

public class SocialuniMusicOperateRecordDOUtils {
    public static SocialuniAppOperateRecordDO createMusicRecord(Long contentId, String type, Long userId, String remark, String roleId) {
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
