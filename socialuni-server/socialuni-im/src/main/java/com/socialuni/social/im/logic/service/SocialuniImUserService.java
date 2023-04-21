package com.socialuni.social.im.logic.service;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.im.api.model.RO.SocialuniImUserDetailRO;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniUserChatConfigDO;
import com.socialuni.social.im.logic.manage.SocialuniUserChatConfigManage;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class SocialuniImUserService {

    public ResultRO<SocialuniImUserDetailRO> getImUserDetail(@RequestBody @Valid SocialuniUserIdQO socialuniUserIdQO) {

        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialuniUserIdQO.getUserId());

        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(beUserId);

        SocialuniImUserDetailRO socialuniImUserDetailRO = new SocialuniImUserDetailRO();

        socialuniImUserDetailRO.setAllowStrangerMsg(socialuniUserChatConfigDO.getAllowStrangerMsg());
        if (mineUserId != null) {
            ChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, beUserId, .class);

        }


        return null;
    }

    public ResultRO<SocialuniImUserDetailRO> getImMineUser() {
        return null;
    }
}
