package com.socialuni.center.web.service;


import com.socialuni.center.web.domain.hug.SocialAddHugDomain;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.QO.community.SocialHugAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialHugAddQO socialHugAddQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        socialAddHugDomain.addHug(mineUser, socialHugAddQO);
        return new ResultRO<>();
    }
}
