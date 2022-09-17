package com.socialuni.sdk.logic.service;


import com.socialuni.sdk.logic.domain.hug.SocialAddHugDomain;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.SocialuniHugAddQO;
import com.socialuni.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.UnionIdUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialuniHugAddQO centerHugAddQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer talkId = UnionIdUtil.getUnionIdByUuidNotNull(centerHugAddQO.getTalkId());

        socialAddHugDomain.addHug(mineUser, new SocialHugAddQO(talkId, null));
        return new ResultRO<>();
    }
}
