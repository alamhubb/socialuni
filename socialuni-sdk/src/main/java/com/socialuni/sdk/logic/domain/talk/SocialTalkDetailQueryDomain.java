package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.factory.SocialTalkROFactory;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.TalkUtils;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

@Service
public class SocialTalkDetailQueryDomain {

    public SocialTalkRO queryTalkDetail(SocialTalkIdQO socialTalkIdQO, SocialuniUserDO mineUser) {
        SocialTalkDO talkDO = TalkUtils.getNotNull(socialTalkIdQO.getTalkId());
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
        SocialTalkRO talkRO = SocialTalkROFactory.getTalkDetailPageTalkRO(mineUser, talkDO, true);

        return talkRO;
    }
}