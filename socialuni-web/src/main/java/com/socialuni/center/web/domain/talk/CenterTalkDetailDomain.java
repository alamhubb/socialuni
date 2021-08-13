package com.socialuni.center.web.domain.talk;

import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.sdk.domain.talk.SocialTalkDetailQueryDomain;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Component
@Slf4j
public class CenterTalkDetailDomain {
    @Resource
    SocialTalkDetailQueryDomain socialTalkDetailQueryDomain;

    public CenterTalkRO queryTalkDetail(CenterTalkIdQO centerTalkIdQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();

        Integer talkId = UnionIdDbUtil.getTalkIdByUnionId(centerTalkIdQO.getTalkId());

        SocialTalkIdQO socialTalkIdQO = new SocialTalkIdQO(talkId);

        SocialTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);

        CenterTalkRO centerTalkRO = CenterTalkROFactory.getTalkRO(socialTalkRO, mineUser);
        return centerTalkRO;
    }
}
