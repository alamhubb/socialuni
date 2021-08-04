package com.socialuni.sdk.service;


import com.socialuni.sdk.domain.hug.SocialAddHugDomain;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.HugAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(HugAddQO hugAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialAddHugDomain.addHug(mineUser, hugAddQO);
        return new ResultRO<>();
    }
}
