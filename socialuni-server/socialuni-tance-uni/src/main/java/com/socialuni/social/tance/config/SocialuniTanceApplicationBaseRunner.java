package com.socialuni.social.tance.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.tance.entity.DevAccountEntity;
import com.socialuni.social.tance.model.DO.AppConfigDO;
import com.socialuni.social.tance.repository.AppConfigRepository;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.constant.AppConfigDOKeyConst;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfigBO;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfigInterface;
import com.socialuni.social.tance.sdk.config.SocialuniAppMoreConfigBO;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    //devId从0开始，可修改默认0的配置，开发者从1开始，0为默认值使用的
    @Resource
    SocialuniAppConfigInterface socialuniAppConfigInterface;


    @Resource
    DevAccountInterface devAccountInterface;

    @Override
    @Async
    public void run(ApplicationArguments args) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        //如果为null，则为default类型
        //初始化默认值
//        log.info("系统配置表数据：{},{}", JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppConfig()), JsonUtil.objectMapper.writeValueAsString(SocialuniAppConfig.getAppMoreConfig()));

        //配置文件里配置的是1的默认的，修改以后，就不再使用了，就判断有没有1，没有的时候创建的时候从配置文件里面拿

        //设置系统的默认值
        CompletableFuture.supplyAsync(() -> {
            this.resetDefaultAppConfigs();
            return null;
        }).exceptionally(e -> {
            e.printStackTrace();
            log.info(e.getMessage());
            return null;
        });

        SocialuniAppConfigBO socialuniAppConfigBO = socialuniAppConfigInterface.getAppConfig();

        //每次启动，都用系统默认值，替换insert中的值

        DevAccountModel devAccountModel = DevAccountFacade.getDevAccount(1);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountModel == null) {
            String phoneNum = socialuniAppConfigBO.getSystemUserPhoneNum();

            //copy一个default的值

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

        DevAccountModel devAccountModelTest = DevAccountFacade.getDevAccount(2);

        //测试渠道的账号
        String phoneNumTest = socialuniAppConfigBO.getTestUserPhoneNum();
        //如果手机号已经存在账户，则直接使用，正序获取第一个用户

        log.info("phoneNumTest:{}", phoneNumTest);
        log.info("devAccountModelTest:{}", devAccountModelTest);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountModelTest == null) {
            //copy一个default的值
            devAccountModelTest = devAccountEntity.createDevAccount(phoneNumTest);
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

    private void resetDefaultAppConfigs() {
        List<AppConfigDO> list = new ArrayList<>();

        SocialuniAppConfigBO socialuniAppConfigBO = socialuniAppConfigInterface.getAppConfig();
        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = socialuniAppConfigInterface.getAppMoreConfig();

        for (String configKey : AppConfigDOKeyConst.configKeys) {
            AppConfigDO configDO1 = new AppConfigDO();
            configDO1.setDevId(0);
            configDO1.setConfigKey(configKey);

            if (configKey.equals(AppConfigDOKeyConst.appGender)) {
                configDO1.setValue(socialuniAppConfigBO.getAppGender());
            } else if (configKey.equals(AppConfigDOKeyConst.systemUserPhoneNum)) {
                configDO1.setValue(socialuniAppConfigBO.getSystemUserPhoneNum());
            } else if (configKey.equals(AppConfigDOKeyConst.followTabName)) {
                configDO1.setValue(socialuniAppConfigBO.getFollowTabName());
            } else if (configKey.equals(AppConfigDOKeyConst.homeTabName)) {
                configDO1.setValue(socialuniAppConfigBO.getHomeTabName());
            } else if (configKey.equals(AppConfigDOKeyConst.cityTabName)) {
                configDO1.setValue(socialuniAppConfigBO.getCityTabName());
            } else if (configKey.equals(AppConfigDOKeyConst.tabNames)) {
                configDO1.setValue(StringUtils.join(socialuniAppConfigBO.getTabNames(), ","));
            } else if (configKey.equals(AppConfigDOKeyConst.defaultChatGroups)) {
                configDO1.setValue(StringUtils.join(socialuniAppConfigBO.getDefaultChatGroups(), ","));
            } else if (configKey.equals(AppConfigDOKeyConst.disableUnderageContent)) {
                configDO1.setValue(socialuniAppConfigBO.getDisableUnderageContent().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.disableContentHasQrCode)) {
                configDO1.setValue(socialuniAppConfigBO.getDisableContentHasQrCode().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.disableContentHasContactInfo)) {
                configDO1.setValue(socialuniAppConfigBO.getDisableContentHasContactInfo().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.mustSetSchoolCanPost)) {
                configDO1.setValue(socialuniAppConfigBO.getMustSetSchoolCanPost().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.serviceWeChat)) {
                //moreConfig
                configDO1.setValue(socialuniAppMoreConfigBO.getServiceWeChat());
            } else if (configKey.equals(AppConfigDOKeyConst.vipPrice)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getVipPrice().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.swiperHeight)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getSwiperHeight().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.homeUrl)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getHomeUrl());
            } else if (configKey.equals(AppConfigDOKeyConst.suggestUrl)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getSuggestUrl());
            } else if (configKey.equals(AppConfigDOKeyConst.contactUsUrl)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getContactUsUrl());
            } else if (configKey.equals(AppConfigDOKeyConst.userAgreementUrl)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getUserAgreementUrl());
            } else if (configKey.equals(AppConfigDOKeyConst.userPrivacyUrl)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getUserPrivacyUrl());
            } else if (configKey.equals(AppConfigDOKeyConst.childProtectUrl)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getChildProtectUrl());
            } else if (configKey.equals(AppConfigDOKeyConst.rewardedAdLimit)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getRewardedAdLimit().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.contactExpenseShell)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getContactExpenseShell().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.contactUserReceiveShell)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getContactUserReceiveShell().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.errorMsgContactService)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getErrorMsgContactService());
            } else if (configKey.equals(AppConfigDOKeyConst.errorMsg601UnLogin)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getErrorMsg601UnLogin());
            } else if (configKey.equals(AppConfigDOKeyConst.errorMsg604SystemError)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getErrorMsg604SystemError());
            } else if (configKey.equals(AppConfigDOKeyConst.authCodeInterval)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getAuthCodeInterval().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.reportCountHide)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getReportCountHide().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.showSwipers)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getShowSwipers().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.talkShowAdInterval)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getTalkShowAdInterval().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.talkShowAdCount)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getTalkShowAdCount().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.authCodeCount)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getAuthCodeCount().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.authCodeIpCount)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getAuthCodeIpCount().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.talkShowAdIndexList)) {
                configDO1.setValue(StringUtils.join(socialuniAppMoreConfigBO.getTalkShowAdIndexList(), ","));
            } else if (configKey.equals(AppConfigDOKeyConst.authCodePhoneCount)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getAuthCodePhoneCount().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.authCodeValidMinute)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getAuthCodeValidMinute().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.highLimitReportCount)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getHighLimitReportCount().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.qq_account)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getQq_account());
            } else if (configKey.equals(AppConfigDOKeyConst.wx_mp_id)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getWx_mp_id());
            } else if (configKey.equals(AppConfigDOKeyConst.wx_app_id)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getWx_app_id());
            } else if (configKey.equals(AppConfigDOKeyConst.wx_mp_secret)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getWx_mp_secret());
            } else if (configKey.equals(AppConfigDOKeyConst.wx_merchant_id)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getWx_merchant_id());
            } else if (configKey.equals(AppConfigDOKeyConst.wx_merchant_key)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getWx_merchant_key());
            } else if (configKey.equals(AppConfigDOKeyConst.devPublishDataApiUrl)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getDevPublishDataApiUrl());
            } else if (configKey.equals(AppConfigDOKeyConst.mp_wx_auditing)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getMp_wx_auditing().toString());
            } else if (configKey.equals(AppConfigDOKeyConst.mp_qq_auditing)) {
                configDO1.setValue(socialuniAppMoreConfigBO.getMp_qq_auditing().toString());
            }
            list.add(configDO1);
        }
        appConfigRepository.saveAll(list);
    }
}
