package com.socialuni.social.music.sdk.check;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.logic.entity.SocialuniMusicRoomUserEntity;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomManage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMusicOperateCheck {
    @Resource
    SocialuniMusicRoomUserEntity socialuniMusicRoomUserEntity;
    @Resource
    SocialuniMusicRoomManage socialuniMusicRoomManage;

    public void checkRoleId(Long chatId, Long mineUserId) {

        //然后是查询roomUser
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = socialuniMusicRoomUserEntity.checkAndGetOrCreateMusicRoomUserInfo(chatId, mineUserId);

        //获取用户在房间的权限
        //判断用户是否有播放权限

        if (socialuniMusicRoomUserDO == null) {
            throw new SocialBusinessException("用户没有操作权限");
        }

       /* String roleId = socialuniMusicRoomUserDO.getRoomRoleId();


        if (StringUtils.isBlank(roleId) || !SocialuniMusicRoleId.hasOperateAuthList.contains(roleId)) {
            throw new SocialBusinessException("用户没有操作权限");
        }*/
    }
}
