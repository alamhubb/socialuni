package com.socialuni.social.music.sdk.check;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.music.sdk.constant.SocialuniMusicRoleId;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicOperateCheckRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniMusicOperateCheck {
    @Resource
    SocialuniChatUserCheck socialuniChatUserCheck;

    public SocialuniMusicOperateCheckRO checkRoleId(String channel) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        Integer mineUserId = mineUser.getUserId();
        Integer chatId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(channel);

        //获取用户权限
        //获取用户，如果用户为系统用户，过滤先走下面
        //首先判断用户的权限，roomuser表
        //如果用户为房间创建者，

        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = SocialuniUserRepositoryFacede.findByUserIdAndCustomFieldAndStatus(mineUserId, "roomId", chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomUserDO.class);

        if (socialuniMusicRoomUserDO == null) {
            //查询用户在房间的权限
            SocialuniChatUserDO socialuniChatUserDO = socialuniChatUserCheck.CheckUserInChat(chatId, mineUserId);

            socialuniMusicRoomUserDO = new SocialuniMusicRoomUserDO(mineUserId, chatId, socialuniChatUserDO.getChatRoleId());

//            if (socialuniChatUserDO.getChatRoleId())

        }
        String roleId = socialuniMusicRoomUserDO.getRoomRoleId();
        if (StringUtils.isBlank(roleId) || !SocialuniMusicRoleId.hasOperateAuthList.contains(roleId)) {
            throw new SocialBusinessException("用户没有操作权限");
        }
        return new SocialuniMusicOperateCheckRO(chatId, mineUserId, roleId);
    }
}
