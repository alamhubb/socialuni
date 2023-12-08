package com.socialuni.social.music.sdk.logic.entity;


import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomManage;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomUserManage;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicOperateCheckRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMusicRoomUserEntity {
    @Resource
    SocialuniChatUserCheck socialuniChatUserCheck;

    @Resource
    SocialuniMusicRoomManage socialuniMusicRoomManage;

    @Resource
    SocialuniMusicRoomUserManage socialuniMusicRoomUserManage;

    //未使用
    public SocialuniMusicOperateCheckRO checkAndGetOrCreateMusicRoomUserInfo(String channel, Integer mineUserId) {
        Integer chatId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(channel);

        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserCheck.checkUserInChat(chatId, mineUserId);

        //然后创建room

        //获取roleId，没有就创建，然后判断musicRoleId

        //然后创建room
        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.getOrCreateMusicPlayerDO(chatId);

        //然后是查询roomUser
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = null;
        if (socialuniChatUserDO != null) {
            socialuniMusicRoomUserDO = socialuniMusicRoomUserManage.getOrCreateMusicRoomUserDO(
                    chatId,
                    socialuniChatUserDO.getUserId(),
                    socialuniChatUserDO.getChatRoleId()
            );
        }

        return new SocialuniMusicOperateCheckRO(chatId, mineUserId, socialuniMusicRoomDO, socialuniMusicRoomUserDO);
    }

    public SocialuniMusicRoomUserDO checkAndGetOrCreateMusicRoomUserInfo(Integer chatId, Integer mineUserId) {
        //校验是否有会话权限
        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserCheck.checkUserInChat(chatId, mineUserId);

        //然后是查询roomUser
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = null;
        if (socialuniChatUserDO != null) {
            //然后创建room
            socialuniMusicRoomUserDO = socialuniMusicRoomUserManage.getOrCreateMusicRoomUserDO(
                    chatId,
                    socialuniChatUserDO.getUserId(),
                    socialuniChatUserDO.getChatRoleId()
            );
            return socialuniMusicRoomUserDO;
        }
        return null;
    }

    public SocialuniMusicRoomDO getOrCreateMusicRoomInfo(Integer chatId) {

        //然后创建room
        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.getOrCreateMusicPlayerDO(chatId);

        return socialuniMusicRoomDO;
    }
}

