package com.socialuni.admin.web.service;

import com.socialuni.admin.web.controller.DevAccountRO;
import com.socialuni.admin.web.controller.DevAccountUpdateQO;
import com.socialuni.sdk.constant.platform.SocialuniSupportProviderType;
import com.socialuni.sdk.model.QO.dev.SyncProdDevAccountQO;
import com.socialuni.sdk.dao.redis.DevAccountRedis;
import com.socialuni.sdk.dao.repository.dev.DevAccountProviderRepository;
import com.socialuni.sdk.dao.repository.dev.DevAccountRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.dao.DO.dev.DevAccountProviderDO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.DevAccountType;
import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.sdk.constant.MpType;
import com.socialuni.sdk.constant.platform.PlatformType;
import com.socialuni.sdk.logic.manage.SocialTagManage;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.utils.UUIDUtil;
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
    private DevAccountRedis devAccountRedis;
    @Resource
    private DevAccountRepository devAccountRepository;
    @Resource
    private DevAccountProviderRepository devAccountProviderRepository;
    @Resource
    private TagRepository tagRepository;
    @Resource
    private SocialTagManage socialTagManage;

    @Transactional
    public ResultRO<DevAccountRO> updateDevAccount(DevAccountUpdateQO devAccountQO) {
        DevAccountDO devAccountDO = DevAccountUtils.getAdminDevAccountNotNull();


        Integer devId = devAccountDO.getId();

        String devType = devAccountQO.getType();
        DevAccountType.checkSupportType(devType);
        String appName = devAccountQO.getAppName();


        String wxMpAppId = devAccountQO.getWxMpAppId();
        String wxMpAppName = devAccountQO.getWxMpAppName();
        String qqMpAppId = devAccountQO.getQqMpAppId();
        String qqMpAppName = devAccountQO.getQqMpAppName();

        DevAccountDO nameCheckDevAccount = devAccountRepository.findOneByAppName(appName);
        //名称已被注册，不为空，还不为当前账户
        if (nameCheckDevAccount != null && !nameCheckDevAccount.getId().equals(devAccountDO.getId())) {
            throw new SocialBusinessException("开发者名称已被注册，请改名后重试");
        }
        //如果创建过，则一定有，所以下面可以直接使用
        TagDO checkNameTag = tagRepository.findFirstByName(appName);
        //tag名称已被注册，不为空，还不为当前用户
        if (checkNameTag != null && !checkNameTag.getDevId().equals(devAccountDO.getId())) {
            throw new SocialBusinessException("开发者名称已被注册，请改名后重试");
        }

        //qq和微信都为空
        if (StringUtils.isEmpty(wxMpAppId) && StringUtils.isEmpty(qqMpAppId)) {
            throw new SocialParamsException("请至少填写一个小程序信息");
        }

        List<DevAccountProviderDO> createDevProviders = new ArrayList<>();

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
            DevAccountProviderDO wxDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByAppIdAndMpType(wxMpAppId, SocialuniSupportProviderType.wx);
            //已存在
            if (wxDevAccountProviderDO != null) {
                //是否与当前开发者id一致，不一致报错，已被使用
                if (!wxDevAccountProviderDO.getDevId().equals(devId)) {
                    throw new SocialParamsException("小程序已被其他开发者绑定，请联系客服处理");
                }
            }
            //查找开发者是否已绑定
            wxDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByDevAndMpType(devId, SocialuniSupportProviderType.wx);
            //只有为空，或者appId新旧不一致，或者appname新旧不一致，才更新
            if (wxDevAccountProviderDO == null
                    || !wxMpAppId.equals(wxDevAccountProviderDO.getAppId())
                    || !wxMpAppName.equals(wxDevAccountProviderDO.getAppName())) {
                //未绑定创建
                if (wxDevAccountProviderDO == null) {
                    wxDevAccountProviderDO = new DevAccountProviderDO(devId, PlatformType.mp, MpType.wx);
                }
                //已绑定更新
                wxDevAccountProviderDO.setAppId(wxMpAppId);
                wxDevAccountProviderDO.setAppName(wxMpAppName);
                wxDevAccountProviderDO = devAccountProviderRepository.save(wxDevAccountProviderDO);
                createDevProviders.add(wxDevAccountProviderDO);
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
            DevAccountProviderDO qqDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByAppIdAndMpType(qqMpAppId, SocialuniSupportProviderType.qq);
            //已存在
            if (qqDevAccountProviderDO != null) {
                //是否与当前开发者id一致，不一致报错，已被使用
                if (!qqDevAccountProviderDO.getDevId().equals(devId)) {
                    throw new SocialParamsException("小程序已被其他开发者绑定，请联系客服处理");
                }
            }
            //查找开发者是否已绑定
            qqDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByDevAndMpType(devId, SocialuniSupportProviderType.qq);


            //只有为空，或者appId新旧不一致，或者appname新旧不一致，才更新
            if (qqDevAccountProviderDO == null
                    || !qqMpAppId.equals(qqDevAccountProviderDO.getAppId())
                    || !qqMpAppName.equals(qqDevAccountProviderDO.getAppName())) {
                //未绑定创建
                if (qqDevAccountProviderDO == null) {
                    qqDevAccountProviderDO = new DevAccountProviderDO(devId, PlatformType.mp, MpType.qq);
                }
                //已绑定更新
                qqDevAccountProviderDO.setAppId(qqMpAppId);
                qqDevAccountProviderDO.setAppName(qqMpAppName);
                qqDevAccountProviderDO = devAccountProviderRepository.save(qqDevAccountProviderDO);
                createDevProviders.add(qqDevAccountProviderDO);
            }
        }

        //        devAccountDO.setType(devType); 先不支持修改
//        devAccountDO.setRealName(devAccountQO.getRealName());
        //更新开发者信息
        devAccountDO.setUpdateTime(new Date());

        boolean isCreateDevAccount = false;
        //如果app名称是空，则代表首次设置，则生成tag名称
        //如果新旧不一样，则肯定要同步，就是要区分是更新，还是新建的问题
        //如果名称不一致，则代表修改了app名称
        if (!appName.equals(devAccountDO.getAppName())) {
            //如果之前为空，则代表初始化
            if (StringUtils.isEmpty(devAccountDO.getAppName())) {
                isCreateDevAccount = true;
                //不能拿到外层，因为有判断
                devAccountDO.setAppName(appName);
                //初始化时生成uuid
                devAccountDO.setSecretKey(UUIDUtil.getUUID());
                //初始化时需要让开发环境同步增加tag
                //设置对应的开发者app名称
                socialTagManage.createDevAccountTagDO(devAccountDO.getId(), appName);
            } else {
                devAccountDO.setAppName(appName);
                //改名，更新tag名称
                checkNameTag.setName(appName);
                tagRepository.save(checkNameTag);
            }
        }

        //新建和修改，数量必然大于0，代表需要向开发环境同步
        SyncProdDevAccountQO syncProdDevAccountQO = new SyncProdDevAccountQO(devAccountDO, createDevProviders);
        //调用api的时候，要区分出来是更新还是新增，尽早区分
//        socialuniAdminAPI.syncProdDevAccount(syncProdDevAccountQO);

        devAccountDO = devAccountRedis.saveDevAccount(devAccountDO);
        DevAccountRO devAccountRO = new DevAccountRO(devAccountDO);
        //如果为创建，首次则返回秘钥
        if (isCreateDevAccount) {
            devAccountRO.setSecretKey(devAccountDO.getSecretKey());
        }
        //则更新用户手机号
        return new ResultRO<>(devAccountRO);
    }
}
