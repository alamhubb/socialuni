package com.socialuni.center.web.manage;

import com.socialuni.center.web.model.DO.ThirdUserDO;
import com.socialuni.center.web.repository.ThirdUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class ThirdUserManage {
    @Resource
    private ThirdUserRepository thirdUserRepository;

    public ThirdUserDO getOrCreate(Integer devId, Integer userId) {
        //需要确认用户是否已经授权过，
        ThirdUserDO threeUserDO = thirdUserRepository.findByDevIdAndUserId(devId, userId);
        //如果已经授权过，则获取，如果没授权过则新创建，因为只生成一次threeUserId
        if (threeUserDO == null) {
            threeUserDO = new ThirdUserDO(devId, userId);

            threeUserDO = thirdUserRepository.save(threeUserDO);
        }
        return threeUserDO;
    }
}
