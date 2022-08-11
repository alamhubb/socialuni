package com.socialuni.sdk.manage;/*
package com.socialuni.sdk.manage;

import com.socialuni.sdk.model.RO.SocialContentUserRO;
import com.socialuni.sdk.utils.model.DO.SocialUserDOFactory;
import com.socialuni.uniapp.model.SocialProviderLoginQO;
import com.socialuni.user.model.DO.UniUserDO;
import com.socialuni.user.sdk.repository.SocialUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonUserDOCreateManage {
    @Resource
    SocialUserRepository socialUserRepository;

    @Resource
    CommonUserDOCheckManage socialUserCheckManage;

    //两种情况，一种手机号登录，一种三方登录
    //有一个统一校验，无论哪个，走了这一个都要能通过
    public UniUserDO create(String platform, String provider, SocialContentUserRO userDetailVO) {
        UniUserDO userDO = SocialUserDOFactory.userOAuthROToDO(userDetailVO);
        userDO.setPlatform(platform);
        userDO.setProvider(provider);
        userDO = socialUserRepository.save(userDO);
        return userDO;
    }

    public UniUserDO createSaveCommonUserDO(SocialProviderLoginQO loginQO) {
        UniUserDO user = SocialUserDOFactory.providerUserLoginQOToDO(loginQO);
        user = socialUserCheckManage.checkSetDefaultValue(user);
        user = socialUserRepository.save(user);
        return user;
    }
}
*/
