package com.socialuni.social.music.sdk.check;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.music.sdk.constant.SocialuniMusicRoleId;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomUserManage;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicOperateCheckRO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMusicOperateCheck {
    @Resource
    SocialuniMusicRoomUserManage socialuniMusicRoomUserManage;

    public SocialuniMusicOperateCheckRO checkRoleId(String channel) {
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = socialuniMusicRoomUserManage.getOrCreateMusicRoomUserDO(channel);

        Integer chatId = socialuniMusicRoomUserDO.getRoomId();

        Integer mineUserId = socialuniMusicRoomUserDO.getUserId();

        String roleId = socialuniMusicRoomUserDO.getRoomRoleId();
        if (StringUtils.isBlank(roleId) || !SocialuniMusicRoleId.hasOperateAuthList.contains(roleId)) {
            throw new SocialBusinessException("用户没有操作权限");
        }
        return new SocialuniMusicOperateCheckRO(chatId, mineUserId, roleId);
    }
}
