package com.socialuni.social.music.sdk.logic.entity;


import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.music.sdk.constant.SocialuniMusicRoleId;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerInfoROFactory;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomManage;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomUserManage;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicOperateCheckRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicRoomInfoRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
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

    public SocialuniMusicOperateCheckRO getOrCreateMusicRoomUser(String channel) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer chatId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(channel);

        SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserCheck.CheckUserInChat(chatId, mineUserId);

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
}

