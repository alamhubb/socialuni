package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.sdk.logic.domain.hug.SocialAddHugDomain;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.QO.SocialuniHugAddQO;
import com.socialuni.social.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialuniHugAddQO centerHugAddQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer talkId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(centerHugAddQO.getTalkId());

        socialAddHugDomain.addHug(mineUser, new SocialHugAddQO(talkId, null));
        return new ResultRO<>();
    }
}
