package com.socialuni.social.sdk.domain.talk;

import com.socialuni.social.sdk.constant.status.ContentStatus;
import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.social.sdk.factory.SocialTalkROFactory;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.sdk.utils.TalkUtils;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

@Service
public class SocialTalkDetailQueryDomain {

    public SocialTalkRO queryTalkDetail(SocialTalkIdQO socialTalkIdQO, UserDO mineUser) {
        TalkDO talkDO = TalkUtils.get(socialTalkIdQO.getTalkId());
        //自己的动态可以看预审核
        if (SocialUserUtil.isMine(mineUser, talkDO.getUserId())) {
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