package com.socialuni.social.tance.entity;

import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import com.socialuni.social.tance.sdk.constant.AdminAppConfigConst;
import com.socialuni.social.tance.sdk.enumeration.DevAccountType;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.tance.sdk.enumeration.SocialFeignHeaderName;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.user.sdk.logic.entity.SocialUserPhoneEntity;
import com.socialuni.social.user.sdk.model.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
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
    private DevAccountInterface devAccountApi;
    @Resource
    private DevAccountRedisInterface devAccountRedis;
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;

    @Resource
    SocialUserPhoneEntity socialUserPhoneEntity;

    public DevAccountModel createDevAccount(String phoneNum) {
        return this.createDevAccount(phoneNum, UUIDUtil.getUUID());
    }

    //创建开发者账号
    public DevAccountModel createDevAccount(String phoneNum, String socialuniId) {
        Optional<? extends DevAccountModel> devAccountDOOptional = devAccountApi.findFirstByOrderByIdDesc();
        //加30以内随机数
        Long curDevNum;
        if (devAccountDOOptional.isPresent()) {
            Long lastDevId = devAccountDOOptional.get().getDevNum();
            curDevNum = lastDevId + new Double((Math.random() * 20)).longValue();
        } else {
            curDevNum = AdminAppConfigConst.qingChiDevNum;
        }
        //加30以内随机数
        DevAccountDo devAccountModel = new DevAccountDo();
        Date curDate = new Date();
        String secretKey = UUIDUtil.getUUID();
        devAccountModel.setSecretKey(secretKey);
        devAccountModel.setPhoneNum(phoneNum);
        devAccountModel.setIdentityNum(null);
        devAccountModel.setSecretKey(UUIDUtil.getUUID());
        devAccountModel.setAppGenderType(GenderType.all);
        devAccountModel.setDevNum(curDevNum);
        devAccountModel.setType(DevAccountType.personal);
        devAccountModel.setStatus(CommonStatus.enable);
        devAccountModel.setCreateTime(curDate);
        devAccountModel.setCallApiCount(0);
        devAccountModel.setUpdateTime(curDate);
        devAccountModel.setSocialuniId(socialuniId);
        devAccountModel = (DevAccountDo) devAccountRedis.saveDevAccount(devAccountModel);

        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        //如果没注册账号，则直接注册
        SocialuniUserDo socialuniUserDo;
        if (SocialUserPhoneDo == null) {
            //如果没注册账号，则直接注册
            socialuniUserDo = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        } else {
            socialuniUserDo = SocialuniUserUtil.getUserNotNull(SocialUserPhoneDo.getUserId());
        }
        devAccountModel.setUserId(socialuniUserDo.getUserId());
        devAccountModel = (DevAccountDo) devAccountRedis.saveDevAccount(devAccountModel);

        //创建话题，还要创建用户
       /* TagDO tagDO = SocialTagDOFactory.toTagDO(curDevNum.toString(), "开发者对应的话题", SocialAppConfig.getSystemUserId());
        tagDO = tagRepository.save(tagDO);
        tagDO.setTagTypeId(32);
        tagDO.setDevId(devAccountDO.getId());
        tagDO = tagRepository.save(tagDO);*/
        return devAccountModel;
    }
}
