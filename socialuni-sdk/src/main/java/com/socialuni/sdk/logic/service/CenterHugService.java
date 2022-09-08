package com.socialuni.sdk.logic.service;


import com.socialuni.sdk.logic.domain.hug.SocialAddHugDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.CenterHugAddQO;
import com.socialuni.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(CenterHugAddQO centerHugAddQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();

        Integer talkId = UnionIdDbUtil.getTalkUnionIdByUidNotNull(centerHugAddQO.getTalkId());

        socialAddHugDomain.addHug(mineUser, new SocialHugAddQO(talkId, null));
        return new ResultRO<>();
    }
}
