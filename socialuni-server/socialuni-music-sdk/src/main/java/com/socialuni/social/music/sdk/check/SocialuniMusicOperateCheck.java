package com.socialuni.social.music.sdk.check;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.music.sdk.constant.SocialuniMusicRoleId;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomUserManage;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicOperateCheckRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMusicOperateCheck {
    @Resource
    SocialuniMusicRoomUserManage socialuniMusicRoomUserManage;

    public SocialuniMusicOperateCheckRO checkRoleId(String channel) {
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = socialuniMusicRoomUserManage.getOrCreateMusicRoomDO(channel);

        Integer chatId = socialuniMusicRoomUserDO.getRoomId();

        Integer mineUserId = socialuniMusicRoomUserDO.getUserId();

        String roleId = socialuniMusicRoomUserDO.getRoomRoleId();
        if (StringUtils.isBlank(roleId) || !SocialuniMusicRoleId.hasOperateAuthList.contains(roleId)) {
            throw new SocialBusinessException("用户没有操作权限");
        }
        return new SocialuniMusicOperateCheckRO(chatId, mineUserId, roleId);
    }
}
