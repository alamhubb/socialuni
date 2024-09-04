package com.socialuni.social.sdk.im.logic.domain;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.enumeration.MessageContentType;
import com.socialuni.social.sdk.im.enumeration.MessageType;
import com.socialuni.social.sdk.im.logic.entity.SocialuniMessageEntity;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatUserDOFactory;
import com.socialuni.social.follow.logic.domain.SocialUserFollowDomain;
import com.socialuni.social.follow.logic.manage.SocialuniUserFollowManage;
import com.socialuni.social.follow.dao.DO.SocialuniUserFollowDO;
import com.socialuni.social.follow.model.follow.SocialuniUserFollowAddQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
public class SocialuniImUserFollowDomain extends SocialUserFollowDomain {
    @Resource
    SocialuniUserFollowManage socialuniUserFollowManage;

    @Resource
    SocialuniMessageEntity socialuniMessageEntity;

    @Transactional
    public SocialuniUserFollowDO addFlow(SocialuniUserFollowAddQO addVO) {
        SocialuniUserFollowDO socialuniUserFollowDO = super.addFlow(addVO);

        Long mineUserId = socialuniUserFollowDO.getUserId();
        Long beUserId = socialuniUserFollowDO.getBeUserId();

        boolean beFollow = socialuniUserFollowManage.userHasFollowBeUser(beUserId, mineUserId);
        if (!beFollow) {
            return socialuniUserFollowDO;
        }

        //互相关注，添加 chat
        List<SocialuniChatUserDO> chatSocialuniUserDoS = SocialuniChatUserDOFactory.createSocialuniChatUserDOS(mineUserId, beUserId);

        SocialuniUserDo systemUser = SocialuniUserUtil.getSystemUserNotNull();
        String msg = "互相关注，成为好友了";
        socialuniMessageEntity.sendMsgNotifyList(msg, systemUser, chatSocialuniUserDoS, MessageContentType.text);
        return socialuniUserFollowDO;
    }

    public void cancelFollow(SocialuniUserFollowAddQO addVO) {
        super.cancelFollow(addVO);
    }
}
