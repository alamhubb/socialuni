package com.socialuni.social.admin.service;

import com.socialuni.social.admin.controller.DevAccountRO;
import com.socialuni.social.admin.controller.DevAccountUpdateQO;
import com.socialuni.social.admin.facede.AdminDevAccountFacade;
import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTagDO;
import com.socialuni.social.community.sdk.repository.TagRepository;
import com.socialuni.social.common.api.constant.MpPlatformType;
import com.socialuni.social.sdk.logic.manage.SocialTagManage;
import com.socialuni.social.sdk.model.QO.dev.SyncProdDevAccountQO;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.api.DevAccountProviderInterface;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import com.socialuni.social.tance.sdk.enumeration.DevAccountType;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.sdk.model.DevAccountProviderModler;
import com.socialuni.social.report.sdk.constant.SocialuniSupportProviderType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminAccountService {
    @Resource
    private DevAccountRedisInterface devAccountRedis;
    @Resource
    private DevAccountInterface devAccountApi;
    @Resource
    private DevAccountProviderInterface devAccountProviderApi;
    @Resource
    private TagRepository tagApi;
    @Resource
    private SocialTagManage socialTagManage;
    @Transactional
    public ResultRO<DevAccountRO> updateDevAccount(DevAccountUpdateQO devAccountQO) {
        DevAccountModel devAccountModel = AdminDevAccountFacade.getAdminDevAccountNotNull();


        Integer devId = devAccountModel.getId();

        String devType = devAccountQO.getType();
        DevAccountType.checkSupportType(devType);
        String appName = devAccountQO.getAppName();


        String wxMpAppId = devAccountQO.getWxMpAppId();
        String wxMpAppName = devAccountQO.getWxMpAppName();
        String qqMpAppId = devAccountQO.getQqMpAppId();
        String qqMpAppName = devAccountQO.getQqMpAppName();

        DevAccountModel nameCheckDevAccount = devAccountApi.findOneByAppName(appName);
        //名称已被注册，不为空，还不为当前账户
        if (nameCheckDevAccount != null && !nameCheckDevAccount.getId().equals(devAccountModel.getId())) {
            throw new SocialBusinessException("开发者名称已被注册，请改名后重试");
        }
        //如果创建过，则一定有，所以下面可以直接使用
        SocialuniTagDO checkNameTag = tagApi.findFirstByName(appName);
        //tag名称已被注册，不为空，还不为当前用户
        if (checkNameTag != null && !checkNameTag.getDevId().equals(devAccountModel.getId())) {
            throw new SocialBusinessException("开发者名称已被注册，请改名后重试");
        }

        //qq和微信都为空
        if (StringUtils.isEmpty(wxMpAppId) && StringUtils.isEmpty(qqMpAppId)) {
            throw new SocialParamsException("请至少填写一个小程序信息");
        }

        List<DevAccountProviderModler> createDevProviders = new ArrayList<>();

        //微信渠道有一个不为空
        if (StringUtils.isNotEmpty(wxMpAppId) || StringUtils.isNotEmpty(wxMpAppName)) {
            if (StringUtils.isEmpty(wxMpAppId)) {
                throw new SocialParamsException("微信小程序Id不可为空");
            }
            if (StringUtils.isEmpty(wxMpAppName)) {
                wxMpAppName = appName;
            }
            //只有新旧内容不一致
            //两个都不为空
            //根据绑定小程序查找，是否已被使用
            DevAccountProviderModler wxDevAccountProviderModler = DevAccountFacade.getDevAccountProviderDOByAppIdAndMpType(wxMpAppId, SocialuniSupportProviderType.wx);
            //已存在
            if (wxDevAccountProviderModler != null) {
                //是否与当前开发者id一致，不一致报错，已被使用
                if (!wxDevAccountProviderModler.getDevId().equals(devId)) {
                    throw new SocialParamsException("小程序已被其他开发者绑定，请联系客服处理");
                }
            }
            //查找开发者是否已绑定
            wxDevAccountProviderModler = DevAccountFacade.getDevAccountProviderDOByDevAndMpType(devId, SocialuniSupportProviderType.wx);
            //只有为空，或者appId新旧不一致，或者appname新旧不一致，才更新
            if (wxDevAccountProviderModler == null
                    || !wxMpAppId.equals(wxDevAccountProviderModler.getAppId())
                    || !wxMpAppName.equals(wxDevAccountProviderModler.getAppName())) {
                //未绑定创建
                if (wxDevAccountProviderModler == null) {
                    wxDevAccountProviderModler = new DevAccountProviderModler(devId, PlatformType.mp, MpPlatformType.wx);
                }
                //已绑定更新
                wxDevAccountProviderModler.setAppId(wxMpAppId);
                wxDevAccountProviderModler.setAppName(wxMpAppName);
                wxDevAccountProviderModler = devAccountProviderApi.savePut(wxDevAccountProviderModler);
                createDevProviders.add(wxDevAccountProviderModler);
            }
        }

        //qq渠道有一个不为空
        if (StringUtils.isNotEmpty(qqMpAppId) || StringUtils.isNotEmpty(qqMpAppName)) {
            if (StringUtils.isEmpty(qqMpAppId)) {
                throw new SocialParamsException("qq小程序Id不可为空");
            }
            if (StringUtils.isEmpty(qqMpAppName)) {
                qqMpAppName = appName;
            }
            //两个都不为空
            //根据绑定小程序查找，是否已被使用
            DevAccountProviderModler qqDevAccountProviderModler = DevAccountFacade.getDevAccountProviderDOByAppIdAndMpType(qqMpAppId, SocialuniSupportProviderType.qq);
            //已存在
            if (qqDevAccountProviderModler != null) {
                //是否与当前开发者id一致，不一致报错，已被使用
                if (!qqDevAccountProviderModler.getDevId().equals(devId)) {
                    throw new SocialParamsException("小程序已被其他开发者绑定，请联系客服处理");
                }
            }
            //查找开发者是否已绑定
            qqDevAccountProviderModler = DevAccountFacade.getDevAccountProviderDOByDevAndMpType(devId, SocialuniSupportProviderType.qq);


            //只有为空，或者appId新旧不一致，或者appname新旧不一致，才更新
            if (qqDevAccountProviderModler == null
                    || !qqMpAppId.equals(qqDevAccountProviderModler.getAppId())
                    || !qqMpAppName.equals(qqDevAccountProviderModler.getAppName())) {
                //未绑定创建
                if (qqDevAccountProviderModler == null) {
                    qqDevAccountProviderModler = new DevAccountProviderModler(devId, PlatformType.mp, MpPlatformType.qq);
                }
                //已绑定更新
                qqDevAccountProviderModler.setAppId(qqMpAppId);
                qqDevAccountProviderModler.setAppName(qqMpAppName);
                qqDevAccountProviderModler = devAccountProviderApi.savePut(qqDevAccountProviderModler);
                createDevProviders.add(qqDevAccountProviderModler);
            }
        }

        //        devAccountDO.setType(devType); 先不支持修改
//        devAccountDO.setRealName(devAccountQO.getRealName());
        //更新开发者信息
        devAccountModel.setUpdateTime(new Date());

        boolean isCreateDevAccount = false;
        //如果app名称是空，则代表首次设置，则生成tag名称
        //如果新旧不一样，则肯定要同步，就是要区分是更新，还是新建的问题
        //如果名称不一致，则代表修改了app名称
        if (!appName.equals(devAccountModel.getAppName())) {
            //如果之前为空，则代表初始化
            if (StringUtils.isEmpty(devAccountModel.getAppName())) {
                isCreateDevAccount = true;
                //不能拿到外层，因为有判断
                devAccountModel.setAppName(appName);
                //初始化时生成uuid
                devAccountModel.setSecretKey(UUIDUtil.getUUID());
                //初始化时需要让开发环境同步增加tag
                //设置对应的开发者app名称
                socialTagManage.createDevAccountTagDO(devAccountModel.getId(), appName);
            } else {
                devAccountModel.setAppName(appName);
                //改名，更新tag名称
                checkNameTag.setName(appName);
                tagApi.savePut(checkNameTag);
            }
        }

        //新建和修改，数量必然大于0，代表需要向开发环境同步
        SyncProdDevAccountQO syncProdDevAccountQO = new SyncProdDevAccountQO(devAccountModel, createDevProviders);
        //调用api的时候，要区分出来是更新还是新增，尽早区分
//        socialuniAdminAPI.syncProdDevAccount(syncProdDevAccountQO);

        devAccountModel = devAccountRedis.saveDevAccount(devAccountModel);
        DevAccountRO devAccountRO = new DevAccountRO(devAccountModel);
        //如果为创建，首次则返回秘钥
        if (isCreateDevAccount) {
            devAccountRO.setSecretKey(devAccountModel.getSecretKey());
        }
        //则更新用户手机号
        return new ResultRO<>(devAccountRO);
    }
}
