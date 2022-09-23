package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.sdk.logic.domain.hug.SocialAddHugDomain;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialHugAddQO socialHugAddQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        socialAddHugDomain.addHug(mineUser, socialHugAddQO);
        return new ResultRO<>();
    }
}
