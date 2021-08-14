package com.socialuni.admin.web.manage;

import com.socialuni.admin.web.constant.AdminAppConfigConst;
import com.socialuni.admin.web.constant.AdminUserType;
import com.socialuni.admin.web.controller.DevAccountRepository;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.constant.StatusConst;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2019-06-25 19:59
 */
@Service
public class DevAccountManage {
    @Resource
    private DevAccountRepository devAccountRepository;

    //创建开发者账号
    public DevAccountDO createDevAccount(String phoneNum) {
        Optional<DevAccountDO> devAccountDOOptional = devAccountRepository.findFirstByOrderByIdDesc();
        Long lastDevId;
        if (devAccountDOOptional.isPresent()) {
            lastDevId = devAccountDOOptional.get().getDevNum();
        } else {
            lastDevId = AdminAppConfigConst.qingChiDevNum;
        }
        //加30以内随机数
        Long curDevId = lastDevId + new Double((Math.random() * 20)).longValue();
        DevAccountDO devAccountDO = new DevAccountDO();
        Date curDate = new Date();
        devAccountDO.setPhoneNum(phoneNum);
        devAccountDO.setDevNum(curDevId);
        devAccountDO.setType(AdminUserType.company);
        devAccountDO.setStatus(StatusConst.enable);
        devAccountDO.setCreateTime(curDate);
        devAccountDO.setUpdateTime(curDate);
        return devAccountRepository.save(devAccountDO);
    }
}
