package com.socialuni.social.common.sdk.logic.domain.talk;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.common.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.common.sdk.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.social.common.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

@Service
public class SocialTalkDetailQueryDomain {

    public SocialuniTalkRO queryTalkDetail(SocialTalkIdQO socialTalkIdQO, SocialuniUserDo mineUser) {
        SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(socialTalkIdQO.getTalkId());
        //自己的动态可以看预审核
        if (SocialuniUserUtil.isMine(mineUser, talkDO.getUserId())) {
            if (!ContentStatus.selfCanSeeContentStatus.contains(talkDO.getStatus())) {
                throw new SocialParamsException("无法查看不存在的动态");
            }
        } else {
            if (!ContentStatus.otherCanSeeContentStatus.contains(talkDO.getStatus())) {
                throw new SocialParamsException("无法查看不存在的动态");
            }
        }
        SocialuniTalkRO talkRO = SocialTalkROFactory.getTalkDetailPageTalkRO(mineUser, talkDO, true);

        return talkRO;
    }
}