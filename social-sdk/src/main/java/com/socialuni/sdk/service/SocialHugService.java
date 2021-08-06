package com.socialuni.sdk.service;


import com.socialuni.sdk.domain.hug.SocialAddHugDomain;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.SocialHugAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialHugAddQO socialHugAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialAddHugDomain.addHug(mineUser, socialHugAddQO);
        return new ResultRO<>();
    }
}
