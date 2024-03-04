package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.community.sdk.logic.domain.hug.SocialAddHugDomain;
import com.socialuni.social.community.sdk.model.QO.SocialuniHugAddQO;
import com.socialuni.social.community.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialuniHugAddQO centerHugAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer talkId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(centerHugAddQO.getTalkId());

        socialAddHugDomain.addHug(mineUser, new SocialHugAddQO(talkId, null));
        return new ResultRO<>();
    }
}
