package com.socialuni.sdk.logic.manage;

import com.socialuni.sdk.model.DO.dev.ThirdUserAuthDO;
import com.socialuni.sdk.model.DO.dev.ThirdUserDO;
import com.socialuni.sdk.dao.repository.dev.ThirdUserAuthRepository;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class ThirdUserAuthManage {
    @Resource
    private ThirdUserAuthRepository thirdUserAuthRepository;

    public void getOrCreate(ThirdUserDO threeUserDO, String oAuthType) {
        //查看是否授权过
        ThirdUserAuthDO threeUserAuthDOOptional = thirdUserAuthRepository.findByDevIdAndUserIdAndAuthTypeAndStatus(threeUserDO.getDevId(), threeUserDO.getUserId(), oAuthType, CommonStatus.enable);
        //授权过
        if (threeUserAuthDOOptional == null) {
            //只是记录一个授权记录
            ThirdUserAuthDO threeUserAuthDO = new ThirdUserAuthDO(threeUserDO, oAuthType);

            thirdUserAuthRepository.save(threeUserAuthDO);
        }
    }
}
