package com.socialuni.demo.service;


import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.entity.user.SocialBindUserProviderAccountEntity;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class AccountService {
    @Resource
    SocialBindUserProviderAccountEntity socialBindUserProviderAccountEntity;

    public ResultRO<Void> bindSocialuniAccount(SocialProviderLoginQO socialBindQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialBindUserProviderAccountEntity.bindProviderAccount(mineUser.getId(), socialBindQO);
        return new ResultRO<>();
    }
}
