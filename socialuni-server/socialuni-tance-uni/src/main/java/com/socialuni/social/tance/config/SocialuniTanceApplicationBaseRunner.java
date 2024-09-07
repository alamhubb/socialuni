package com.socialuni.social.tance.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.tance.dev.config.SocialuniAppConfig;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.dao.DO.AppConfigDO;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.dao.repository.AppConfigRepository;
import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.tance.dev.constant.AppConfigDOKeyConst;
import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.logic.manage.SocialuniAppConfigManage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
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
@Order(10001)
public class SocialuniTanceApplicationBaseRunner implements ApplicationRunner {

    @Resource
    AppConfigRepository appConfigRepository;

    @Resource
    DevAccountInterface devAccountInterface;

    @Resource
    DevAccountEntity devAccountEntity;

    @Resource
    SocialuniAppConfigManage socialuniAppConfigManage;

    @Override
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

        //每次启动，都用系统默认值，替换insert中的值
        String phoneNum = SocialuniDevConfig.getSystemUserPhoneNum();

        DevAccountDo devAccountDo = devAccountInterface.findFirstById(1);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountDo == null) {
            //copy一个default的值

            if (StringUtils.isEmpty(SocialuniSystemConst.getAppSocialuniId())) {
                devAccountDo = devAccountEntity.createDevAccount(phoneNum);

            } else {
                devAccountDo = devAccountEntity.createDevAccount(phoneNum, SocialuniSystemConst.getAppSocialuniId());
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

        //测试渠道的账号,干嘛用的呢，方便可以往中心发帖子
        String phoneNumTest = SocialuniDevConfig.getTestUserPhoneNum();

        DevAccountDo devAccountDoTest = devAccountInterface.findOneByPhoneNumOrderByIdAsc(phoneNumTest);

        //如果手机号已经存在账户，则直接使用，正序获取第一个用户

        log.info("phoneNumTest:{}", phoneNumTest);
        log.info("devAccountModelTest:{}", devAccountDoTest);

        //如果不存在用户，则创建第一个默认的主系统开发者
        if (devAccountDoTest == null) {
            //copy一个default的值
            devAccountDoTest = devAccountEntity.createDevAccount(phoneNumTest);
        }

        //创建中心
        if (SocialuniDevConfig.hasCenterServer()) {
            DevAccountDo centerDevDO = DevAccountFacade.getDevAccountBySocialuniId(SocialuniSystemConst.getCenterSocialuniId());
            if (centerDevDO == null) {
                //手机号格式字符串瞎写就行，没有其他地方使用
                devAccountEntity.createDevAccount("99999888667", SocialuniSystemConst.getCenterSocialuniId());
            }
        }
        //获取省，不包含子节点
    }

    private void resetDefaultAppConfigs() {
        SocialuniAppConfigBO socialuniAppConfigBO = SocialuniAppConfig.getAppConfig();
        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = SocialuniAppConfig.getAppMoreConfig();

        Integer devId = 0;
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.appGender, socialuniAppConfigBO.getAppGender());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.followTabName, socialuniAppConfigBO.getFollowTabName());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.homeTabName, socialuniAppConfigBO.getHomeTabName());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.cityTabName, socialuniAppConfigBO.getCityTabName());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.tabNames, StringUtils.join(socialuniAppConfigBO.getTabNames()));
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.defaultChatGroups, StringUtils.join(socialuniAppConfigBO.getDefaultChatGroups()));
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.disableUnderageContent, socialuniAppConfigBO.getDisableUnderageContent());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.disableContentHasQrCode, socialuniAppConfigBO.getDisableContentHasQrCode());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.disableContentHasContactInfo, socialuniAppConfigBO.getDisableContentHasContactInfo());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.mustSetSchoolCanPost, socialuniAppConfigBO.getMustSetSchoolCanPost());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.serviceWeChat, socialuniAppMoreConfigBO.getServiceWeChat());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.vipPrice, socialuniAppMoreConfigBO.getVipPrice());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.swiperHeight, socialuniAppMoreConfigBO.getSwiperHeight());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.homeUrl, socialuniAppMoreConfigBO.getHomeUrl());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.suggestUrl, socialuniAppMoreConfigBO.getSuggestUrl());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.contactUsUrl, socialuniAppMoreConfigBO.getContactUsUrl());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.userAgreementUrl, socialuniAppMoreConfigBO.getUserAgreementUrl());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.userPrivacyUrl, socialuniAppMoreConfigBO.getUserPrivacyUrl());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.childProtectUrl, socialuniAppMoreConfigBO.getChildProtectUrl());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.rewardedAdLimit, socialuniAppMoreConfigBO.getRewardedAdLimit());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.contactExpenseShell, socialuniAppMoreConfigBO.getContactExpenseShell());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.contactUserReceiveShell, socialuniAppMoreConfigBO.getContactUserReceiveShell());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.errorMsgContactService, socialuniAppMoreConfigBO.getErrorMsgContactService());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.errorMsg601UnLogin, socialuniAppMoreConfigBO.getErrorMsg601UnLogin());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.errorMsg604SystemError, socialuniAppMoreConfigBO.getErrorMsg604SystemError());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.authCodeInterval, socialuniAppMoreConfigBO.getAuthCodeInterval());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.reportCountHide, socialuniAppMoreConfigBO.getReportCountHide());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.showSwipers, socialuniAppMoreConfigBO.getShowSwipers());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.talkShowAdInterval, socialuniAppMoreConfigBO.getTalkShowAdInterval());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.talkShowAdCount, socialuniAppMoreConfigBO.getTalkShowAdCount());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.authCodeCount, socialuniAppMoreConfigBO.getAuthCodeCount());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.authCodeIpCount, socialuniAppMoreConfigBO.getAuthCodeIpCount());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.talkShowAdIndexList, socialuniAppMoreConfigBO.getTalkShowAdIndexList());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.authCodePhoneCount, socialuniAppMoreConfigBO.getAuthCodePhoneCount());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.authCodeValidMinute, socialuniAppMoreConfigBO.getAuthCodeValidMinute());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.highLimitReportCount, socialuniAppMoreConfigBO.getHighLimitReportCount());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.qq_account, socialuniAppMoreConfigBO.getQq_account());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.wx_mp_id, socialuniAppMoreConfigBO.getWx_mp_id());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.wx_app_id, socialuniAppMoreConfigBO.getWx_app_id());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.wx_mp_secret, socialuniAppMoreConfigBO.getWx_mp_secret());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.wx_merchant_id, socialuniAppMoreConfigBO.getWx_merchant_id());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.wx_merchant_key, socialuniAppMoreConfigBO.getWx_merchant_key());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.devPublishDataApiUrl, socialuniAppMoreConfigBO.getDevPublishDataApiUrl());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.mp_wx_auditing, socialuniAppMoreConfigBO.getMp_wx_auditing());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.mp_qq_auditing, socialuniAppMoreConfigBO.getMp_qq_auditing());
        socialuniAppConfigManage.updateAppConfig(devId, AppConfigDOKeyConst.sysServiceReceiveRatio, socialuniAppMoreConfigBO.getSysServiceReceiveRatio());
    }
}
