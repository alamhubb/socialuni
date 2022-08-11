package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.model.QO.talk.CenterTalkIdQO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
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
        SocialUserDO mineUser = CenterUserUtil.getMineUserAllowNull();

        Integer talkId = UnionIdDbUtil.getTalkUnionIdByUidNotNull(centerTalkIdQO.getTalkId());

        SocialTalkIdQO socialTalkIdQO = new SocialTalkIdQO(talkId);

        SocialTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);

        CenterTalkRO centerTalkRO = CenterTalkROFactory.getTalkRO(socialTalkRO, mineUser);
        return centerTalkRO;
    }
}
