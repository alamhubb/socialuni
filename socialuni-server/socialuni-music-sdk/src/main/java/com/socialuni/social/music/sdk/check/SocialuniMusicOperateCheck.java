package com.socialuni.social.music.sdk.check;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.music.sdk.constant.SocialuniMusicRoleId;
import com.socialuni.social.music.sdk.logic.entity.SocialuniMusicRoomUserEntity;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicOperateCheckRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMusicOperateCheck {
    @Resource
    SocialuniMusicRoomUserEntity socialuniMusicRoomUserEntity;

    public SocialuniMusicOperateCheckRO checkRoleId(String channel) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniMusicOperateCheckRO socialuniMusicOperateCheckRO = socialuniMusicRoomUserEntity.getOrCreateMusicRoomInfo(channel, mineUserId);

        String roleId = socialuniMusicOperateCheckRO.getRoleId();

        if (StringUtils.isBlank(roleId) || !SocialuniMusicRoleId.hasOperateAuthList.contains(roleId)) {
            throw new SocialBusinessException("用户没有操作权限");
        }
        return socialuniMusicOperateCheckRO;
    }
}
