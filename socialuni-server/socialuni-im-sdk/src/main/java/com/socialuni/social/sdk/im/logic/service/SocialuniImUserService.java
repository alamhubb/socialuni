package com.socialuni.social.sdk.im.logic.service;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.api.model.RO.SocialuniImMineUserDetailRO;
import com.socialuni.social.im.api.model.RO.SocialuniImUserDetailRO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniUserChatConfigDO;
import com.socialuni.social.sdk.im.logic.manage.SocialuniUserChatConfigManage;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserBlackDO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class SocialuniImUserService {

    public ResultRO<SocialuniImUserDetailRO> getImUserDetail(@RequestBody @Valid SocialuniUserIdQO socialuniUserIdQO) {

        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialuniUserIdQO.getUserId());

        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        //获取对方是否接收陌生人消息
        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(beUserId);

        SocialuniImUserDetailRO socialuniImUserDetailRO = new SocialuniImUserDetailRO();

        //设置对方是否允许陌生人消息
        socialuniImUserDetailRO.setAllowStrangerMsg(socialuniUserChatConfigDO.getAllowStrangerMsg());
        if (mineUserId != null) {
//            SocialuniChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(beUserId, mineUserId, SocialuniChatUserDO.class);

            SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(mineUserId, beUserId);

            SocialuniUserBlackDO socialuniUserBlackDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndStatus(beUserId, mineUserId, SocialuniCommonStatus.enable, SocialuniUserBlackDO.class);
            if (socialuniUserBlackDO != null) {
                socialuniImUserDetailRO.setBlackUser(true);
            }
            //查看对方是否关注了你
//            socialuniImUserDetailRO.setBeFollow(socialuniUserFollowManage.userHasFollowBeUser(beUserId, mineUserId));
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

    public ResultRO<SocialuniImMineUserDetailRO> closeStrangerMsg() {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(mineUserId);

        socialuniUserChatConfigDO.setAllowStrangerMsg(false);

        socialuniUserChatConfigDO = SocialuniRepositoryFacade.save(socialuniUserChatConfigDO);

        SocialuniImMineUserDetailRO socialuniImMineUserDetailRO = new SocialuniImMineUserDetailRO();
        socialuniImMineUserDetailRO.setAllowStrangerMsg(socialuniUserChatConfigDO.getAllowStrangerMsg());
        return ResultRO.success(socialuniImMineUserDetailRO);
    }

    public ResultRO<SocialuniImMineUserDetailRO> openStrangerMsg() {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(mineUserId);

        socialuniUserChatConfigDO.setAllowStrangerMsg(true);

        socialuniUserChatConfigDO = SocialuniRepositoryFacade.save(socialuniUserChatConfigDO);

        SocialuniImMineUserDetailRO socialuniImMineUserDetailRO = new SocialuniImMineUserDetailRO();
        socialuniImMineUserDetailRO.setAllowStrangerMsg(socialuniUserChatConfigDO.getAllowStrangerMsg());
        return ResultRO.success(socialuniImMineUserDetailRO);
    }
}
