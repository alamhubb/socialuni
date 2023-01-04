package com.socialuni.social.tance.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.tance.entity.DevAccountEntity;
import com.socialuni.social.tance.model.DO.AppConfigDO;
import com.socialuni.social.tance.repository.AppConfigRepository;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.user.sdk.logic.entity.SocialUserPhoneEntity;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 11:57
 */
@Component
@Slf4j
public class SocialuniTanceApplicationBaseRunner implements ApplicationRunner {
    @Resource
    DevAccountEntity devAccountEntity;
    @Resource
    AppConfigRepository appConfigRepository;

    @Override
    @Async
    public void run(ApplicationArguments args) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        //如果为null，则为default类型
        //初始化默认值
//        log.info("系统配置表数据：{},{}", JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppConfig()), JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppMoreConfig()));

        //配置文件里配置的是1的默认的，修改以后，就不再使用了，就判断有没有1，没有的时候创建的时候从配置文件里面拿


        DevAccountModel devAccountModel = DevAccountFacade.getDevAccount(1);

        log.info("执行启动命令");

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountModel == null) {
            String phoneNum = SocialuniSystemConst.getSystemUserPhoneNum();

            //copy一个default的值

            if (StringUtils.isEmpty(SocialuniSystemConst.getAppSocialuniId())) {
                devAccountModel = devAccountEntity.createDevAccount(phoneNum);

            } else {
                devAccountModel = devAccountEntity.createDevAccount(phoneNum, SocialuniSystemConst.getAppSocialuniId());
            }

            AppConfigDO wxIdConfig = new AppConfigDO();
            wxIdConfig.setDevId(devAccountModel.getId());
            wxIdConfig.setConfigKey(AppConfigDOKeyConst.wx_mp_id);
            wxIdConfig.setValue(WxUtil.getWx_mp_id());

            AppConfigDO wxSecretConfig = new AppConfigDO();
            wxSecretConfig.setDevId(devAccountModel.getId());
            wxSecretConfig.setConfigKey(AppConfigDOKeyConst.wx_mp_secret);
            wxSecretConfig.setValue(WxUtil.getWx_mp_secret());

            List<AppConfigDO> list = new ArrayList<>();
            list.add(wxIdConfig);
            list.add(wxSecretConfig);

            appConfigRepository.saveAll(list);


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
