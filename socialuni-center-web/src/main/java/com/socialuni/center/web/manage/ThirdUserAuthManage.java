package com.socialuni.center.web.manage;

import com.socialuni.center.web.model.DO.ThirdUserAuthDO;
import com.socialuni.center.web.model.DO.ThirdUserDO;
import com.socialuni.center.web.repository.ThirdUserAuthRepository;
import com.socialuni.social.constant.CommonStatus;
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
