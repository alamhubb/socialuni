package com.socialuni.social.sdk.logic.entity;

import com.socialuni.social.sdk.constant.AdminAppConfigConst;
import com.socialuni.social.sdk.logic.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.dao.redis.DevAccountRedis;
import com.socialuni.social.community.sdk.api.TagInterface;
import com.socialuni.social.common.enumeration.CommonStatus;
import com.socialuni.social.tance.sdk.enumeration.DevAccountType;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.common.utils.UUIDUtil;
import com.socialuni.social.user.sdk.model.SocialUserPhoneModel;
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
    private DevAccountRedis devAccountRedis;
    @Resource
    private TagInterface tagApi;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;

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
        //同时创建c段账号
        SocialUserPhoneModel socialUserPhoneModel = socialUserPhoneManage.checkLoginPhoneNum(phoneNum);

        //加30以内随机数
        DevAccountModel devAccountModel = new DevAccountModel();
        Date curDate = new Date();
        String secretKey = UUIDUtil.getUUID();
        devAccountModel.setSecretKey(secretKey);
        devAccountModel.setUserId(socialUserPhoneModel.getUserId());
        devAccountModel.setPhoneNum(phoneNum);
        devAccountModel.setIdentityNum(null);
//        devAccountDO.setSecretKey(UUIDUtil.getUUID());
        devAccountModel.setAppGenderType(GenderType.all);
        devAccountModel.setDevNum(curDevNum);
        devAccountModel.setType(DevAccountType.personal);
        devAccountModel.setStatus(CommonStatus.enable);
        devAccountModel.setCreateTime(curDate);
        devAccountModel.setCallApiCount(0);
        devAccountModel.setUpdateTime(curDate);
        devAccountModel.setSocialuniId(socialuniId);
        devAccountModel = devAccountRedis.saveDevAccount(devAccountModel);

        //创建话题，还要创建用户
       /* TagDO tagDO = SocialTagDOFactory.toTagDO(curDevNum.toString(), "开发者对应的话题", SocialAppConfig.getSystemUserId());
        tagDO = tagRepository.save(tagDO);
        tagDO.setTagTypeId(32);
        tagDO.setDevId(devAccountDO.getId());
        tagDO = tagRepository.save(tagDO);*/
        return devAccountModel;
    }
}
