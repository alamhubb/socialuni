package com.socialuni.center.web.service;


import com.socialuni.center.web.domain.hug.SocialAddHugDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.SocialHugAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(SocialHugAddQO socialHugAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        socialAddHugDomain.addHug(mineUser, socialHugAddQO);
        return new ResultRO<>();
    }
}
