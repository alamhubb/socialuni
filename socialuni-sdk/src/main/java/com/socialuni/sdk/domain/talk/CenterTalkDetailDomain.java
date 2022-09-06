package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.sdk.model.QO.talk.CenterTalkIdQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
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

    public CenterTalkRO queryTalkDetail(String talkId) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        Integer talkIdInt = UnionIdDbUtil.getTalkUnionIdByUidNotNull(talkId);

        SocialTalkIdQO socialTalkIdQO = new SocialTalkIdQO(talkIdInt);

        SocialTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);

        CenterTalkRO centerTalkRO = CenterTalkROFactory.getTalkRO(socialTalkRO, mineUser);
        return centerTalkRO;
    }
}
