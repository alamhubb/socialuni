package com.socialuni.social.sdk.logic.domain.talk;

import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.sdk.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Component
@Slf4j
public class SocialuniTalkDetailDomain {
    @Resource
    SocialTalkDetailQueryDomain socialTalkDetailQueryDomain;

    public SocialuniTalkRO queryTalkDetail(String talkId) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        Integer talkIdInt = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(talkId);

        SocialTalkIdQO socialTalkIdQO = new SocialTalkIdQO(talkIdInt);

        SocialuniTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);

        return socialTalkRO;
    }
}
