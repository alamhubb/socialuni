package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.UnionIdUtil;
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

        Integer talkIdInt = UnionIdUtil.getUnionIdByUuidNotNull(talkId);

        SocialTalkIdQO socialTalkIdQO = new SocialTalkIdQO(talkIdInt);

        SocialuniTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);

        return socialTalkRO;
    }
}
