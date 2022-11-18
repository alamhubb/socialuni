package com.socialuni.social.tance.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.tance.entity.DevAccountEntity;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 11:57
 */
@Component
@Slf4j
public class SocialuniTanceApplicationBaseRunner implements ApplicationRunner {
    @Resource
    DevAccountEntity devAccountEntity;

    @Override
    @Async
    public void run(ApplicationArguments args) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        //如果为null，则为default类型
        //初始化默认值
//        log.info("系统配置表数据：{},{}", JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppConfig()), JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppMoreConfig()));

        DevAccountModel devAccountModel = DevAccountFacade.getDevAccount(1);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountModel == null) {
            String phoneNum = SocialuniSystemConst.getSystemUserPhoneNum();
            if (StringUtils.isEmpty(SocialuniSystemConst.getAppSocialuniId())) {
                devAccountModel = devAccountEntity.createDevAccount(phoneNum);
            } else {
                devAccountModel = devAccountEntity.createDevAccount(phoneNum, SocialuniSystemConst.getAppSocialuniId());
            }
            // 注册admin的时候，肯定是没有c端用户的，你开发者都没有怎么可能有他下面的用户呢
            // 所以直接注册c端用户
//            SocialuniUserDo SocialuniUserDo = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
//            devAccountModel.setUserId(SocialuniUserDo.getUserId());
//            devAccountRedisInterface.saveDevAccount(devAccountModel);

            /*DevSocialuniIdDO devSocialuniIdDO = new DevSocialuniIdDO();
            //设置第一个的socialuniId
            devSocialuniIdDO.setSocialuniId(devAccountDO.getSocialuniId());
            devSocialuniIdRepository.save(devSocialuniIdDO);*/
        }
        //创建中心
        if (SocialuniSystemConst.serverIsChild()) {
            DevAccountModel centerDevDO = DevAccountFacade.getDevAccountBySocialuniId(SocialuniSystemConst.getCenterSocialuniId());
            if (centerDevDO == null) {
                //手机号格式字符串瞎写就行，没有其他地方使用
                devAccountEntity.createDevAccount("99999888667", SocialuniSystemConst.getCenterSocialuniId());
            }
        }
        //获取省，不包含子节点
    }
}