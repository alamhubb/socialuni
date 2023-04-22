package com.socialuni.social.im.logic.service;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.api.model.RO.SocialuniImMineUserDetailRO;
import com.socialuni.social.im.api.model.RO.SocialuniImUserDetailRO;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.im.dao.DO.SocialuniUserChatConfigDO;
import com.socialuni.social.im.enumeration.SocialuniAddFriendStatus;
import com.socialuni.social.im.logic.manage.SocialuniUserChatConfigManage;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class SocialuniImUserService {

    public ResultRO<SocialuniImUserDetailRO> getImUserDetail(@RequestBody @Valid SocialuniUserIdQO socialuniUserIdQO) {

        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialuniUserIdQO.getUserId());

        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(beUserId);

        SocialuniImUserDetailRO socialuniImUserDetailRO = new SocialuniImUserDetailRO();

        //设置对方是否允许陌生人消息
        socialuniImUserDetailRO.setAllowStrangerMsg(socialuniUserChatConfigDO.getAllowStrangerMsg());
        if (mineUserId != null) {
            SocialuniChatUserDO beChatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(beUserId, mineUserId, SocialuniChatUserDO.class);
            if (beChatUserDO != null && beChatUserDO.getBlackUser()) {
                socialuniImUserDetailRO.setBeBlackUser(true);
            }
            SocialuniChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, beUserId, SocialuniChatUserDO.class);
            if (chatUserDO != null && chatUserDO.getBlackUser()) {
                socialuniImUserDetailRO.setBlackUser(true);
            }
            SocialuniFriendApplyRecordDO socialuniFriendApplyRecordDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, beUserId, SocialuniFriendApplyRecordDO.class);
            if (socialuniFriendApplyRecordDO != null && socialuniFriendApplyRecordDO.getStatus().equals(SocialuniAddFriendStatus.enable)) {
                socialuniImUserDetailRO.setFriendUser(true);
            }
        }

        return ResultRO.success(socialuniImUserDetailRO);
    }

    public ResultRO<SocialuniImMineUserDetailRO> getImMineUser() {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(mineUserId);

        SocialuniImMineUserDetailRO socialuniImMineUserDetailRO = new SocialuniImMineUserDetailRO();
        socialuniImMineUserDetailRO.setAllowStrangerMsg(socialuniUserChatConfigDO.getAllowStrangerMsg());
        return ResultRO.success(socialuniImMineUserDetailRO);
    }
}
