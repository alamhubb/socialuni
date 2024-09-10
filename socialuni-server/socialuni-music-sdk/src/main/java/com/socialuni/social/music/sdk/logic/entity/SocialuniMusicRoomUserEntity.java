package com.socialuni.social.music.sdk.logic.entity;


import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomManage;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomUserManage;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
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

    @Resource
    SocialuniChatUserManage socialuniChatUserManage;

    //未使用
    public SocialuniMusicRoomUserDO checkAndGetOrCreateMusicRoomUserInfo(Long chatId, Long mineUserId) {

        SocialuniChatDO socialuniChatDO = SocialuniChatDOUtil.getChat(chatId);

        SocialuniChatUserCheck.checkUserCanSeeChat(socialuniChatDO, mineUserId);

        //校验是否有会话权限
        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserManage.joinOrCreateChatUser(socialuniChatDO, mineUserId);

        //然后是查询roomUser
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = this.getOrCreateMusicRoomUserInfo(chatId, socialuniChatUserDO);
        return socialuniMusicRoomUserDO;
    }

    private SocialuniMusicRoomUserDO getOrCreateMusicRoomUserInfo(Long chatId, SocialuniChatUserDO socialuniChatUserDO) {
        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.getOrCreateMusicPlayerDO(chatId);

        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = null;

        if (socialuniChatUserDO != null) {
            socialuniMusicRoomUserDO = socialuniMusicRoomUserManage.getOrCreateMusicRoomUserDO(
                    chatId,
                    socialuniChatUserDO.getUserId(),
                    socialuniChatUserDO.getChatRoleId()
            );
        }
        return socialuniMusicRoomUserDO;
    }
}

