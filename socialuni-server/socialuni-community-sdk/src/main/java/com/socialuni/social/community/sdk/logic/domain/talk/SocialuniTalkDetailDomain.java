package com.socialuni.social.community.sdk.logic.domain.talk;

import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.community.sdk.model.QO.talk.SocialTalkIdQO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        Long talkIdInt = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(talkId);

        SocialTalkIdQO socialTalkIdQO = new SocialTalkIdQO(talkIdInt);

        SocialuniTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);

        return socialTalkRO;
    }
}
