package com.socialuni.admin.web.manage;

import com.socialuni.admin.web.constant.AdminAppConfigConst;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.constant.DevAccountType;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.sdk.redis.DevAccountRedis;
import com.socialuni.social.sdk.repository.community.TagRepository;
import com.socialuni.social.sdk.repository.dev.DevAccountRepository;
import com.socialuni.social.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2019-06-25 19:59
 */
@Service
public class DevAccountEntity {
    @Resource
    private DevAccountRepository devAccountRepository;
    @Resource
    private DevAccountRedis devAccountRedis;
    @Resource
    private TagRepository tagRepository;

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
        Long curDevNum = lastDevId + new Double((Math.random() * 20)).longValue();
        DevAccountDO devAccountDO = new DevAccountDO();
        Date curDate = new Date();
        String secretKey = UUIDUtil.getUUID();
        devAccountDO.setSecretKey(secretKey);
        devAccountDO.setPhoneNum(phoneNum);
//        devAccountDO.setSecretKey(UUIDUtil.getUUID());
        devAccountDO.setAppGenderType(GenderType.all);
        devAccountDO.setDevNum(curDevNum);
        devAccountDO.setType(DevAccountType.personal);
        devAccountDO.setStatus(CommonStatus.enable);
        devAccountDO.setCreateTime(curDate);
        devAccountDO.setUpdateTime(curDate);
        devAccountDO = devAccountRedis.saveDevAccount(devAccountDO);

        //创建话题，还要创建用户
       /* TagDO tagDO = SocialTagDOFactory.toTagDO(curDevNum.toString(), "开发者对应的话题", SocialAppConfig.getSystemUserId());
        tagDO = tagRepository.save(tagDO);
        tagDO.setTagTypeId(32);
        tagDO.setDevId(devAccountDO.getId());
        tagDO = tagRepository.save(tagDO);*/
        return devAccountDO;
    }
}
