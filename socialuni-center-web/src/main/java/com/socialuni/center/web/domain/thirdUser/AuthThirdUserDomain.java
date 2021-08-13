package com.socialuni.center.web.domain.thirdUser;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.entity.AuthThirdUserEntity;
import com.socialuni.center.web.manage.ThirdUserTokenManage;
import com.socialuni.center.web.model.DO.ThirdUserTokenDO;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthThirdUserDomain {
    @Resource
    AuthThirdUserEntity authThirdUserEntity;
    @Resource
    ThirdUserTokenManage thirdUserTokenManage;

    public SocialLoginRO<CenterMineUserDetailRO> authThirdUser(UserDO mineUser, String authType) {
        CenterMineUserDetailRO centerUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, authType);

        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(mineUser.getId(), DevAccountUtils.getDevId());

        //生成返回对象
        SocialLoginRO<CenterMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return applySocialUniOAuthRO;
    }

}
