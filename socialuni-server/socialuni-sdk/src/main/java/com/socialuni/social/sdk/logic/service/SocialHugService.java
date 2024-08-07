package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.community.sdk.logic.domain.hug.SocialAddHugDomain;
import com.socialuni.social.community.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialHugAddQO socialHugAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        socialAddHugDomain.addHug(mineUser, socialHugAddQO);
        return new ResultRO<>();
    }
}
