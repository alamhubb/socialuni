package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.sdk.dao.DO.dev.ThirdUserAuthDO;
import com.socialuni.social.sdk.dao.DO.dev.ThirdUserDO;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserAuthRepository;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class ThirdUserAuthManage {
    @Resource
    private ThirdUserAuthRepository thirdUserAuthRepository;

    public void getOrCreate(ThirdUserDO threeUserDO, String oAuthType) {
        //查看是否授权过
        ThirdUserAuthDO threeUserAuthDOOptional = thirdUserAuthRepository.findByDevIdAndUserIdAndAuthTypeAndStatus(threeUserDO.getDevId(), threeUserDO.getUserId(), oAuthType, SocialuniCommonStatus.enable);
        //授权过
        if (threeUserAuthDOOptional == null) {
            //只是记录一个授权记录
            ThirdUserAuthDO threeUserAuthDO = new ThirdUserAuthDO(threeUserDO, oAuthType);

            thirdUserAuthRepository.save(threeUserAuthDO);
        }
    }
}
