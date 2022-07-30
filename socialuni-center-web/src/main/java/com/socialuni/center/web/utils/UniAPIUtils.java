package com.socialuni.center.web.utils;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.SocialuniUidRO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.model.DO.UniThirdContentDO;
import com.socialuni.center.web.repository.UniThirdContentRepository;
import com.socialuni.cloud.config.SocialAppEnv;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.model.model.QO.ContentAddQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.repository.dev.DevAccountRepository;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import java.net.URI;
import java.util.function.BiFunction;
import java.util.function.Function;


@Component
public class UniAPIUtils {
    static SocialuniUserAPI socialuniUserAPI;
    static UniThirdContentRepository uniThirdContentRepository;

    static DevAccountRepository devAccountRepository;

    @Resource
    public void setDevAccountRepository(DevAccountRepository devAccountRepository) {
        UniAPIUtils.devAccountRepository = devAccountRepository;
    }

    @Resource
    public void setUniThirdContentRepository(UniThirdContentRepository uniThirdContentRepository) {
        UniThirdContentUtils.uniThirdContentRepository = uniThirdContentRepository;
    }

    @Resource
    public void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        UniAPIUtils.socialuniUserAPI = socialuniUserAPI;
    }

    //list转换，TO类List转为RO类List
    public static <QO extends ContentAddQO, RO extends SocialuniUidRO> SocialuniUidRO callUniAPI(String contentType, Function<QO, SocialuniUidRO> domain, BiFunction<URI, QO, ResultRO<RO>> callApi, QO contentAddQO) {

//如果是中心 m则向所有子节点发请求， owner是传过来的， id自己的，秘钥自己的，从数据库里获取，自己对应对方的秘钥

//如果是子应用 则向中心发请求， 秘钥配置在启动设置中， owner是自己的id，id也设置


//如果中心，则是子应用推过来的， 只要看有没有 ownerid就行，怎么都是发所有。

//如果是子应用，则是中心推过来的，肯定会有contentId


        //首先判断有没有秘钥，有秘钥则代表推送


        String thirdId = contentAddQO.getId();

        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        Integer devId = devAccountDO.getId();


        //根据dataSocialuniId获取devAccount

        //如果dataSocialuniId为空，则代表本应用的动态


        //根据有没有配置秘钥来决定是否为中心服务器
        if (DevAccountUtils.isCenterServer()) {

        }

        //不做校验 dataId为空，则无操作
        //不为空则校验thirdId。是否已存在。


        String socialuniId = RequestUtil.getDataSocialuniId();
        DevAccountDO dataDevAccount = null;
        if (StringUtils.isNotEmpty(socialuniId)) {
            dataDevAccount = DevAccountUtils.getDevAccountBySocialuniId(socialuniId);
            if (dataDevAccount == null) {
                throw new SocialParamsException("不存在的联盟Id");
            }
            Integer dataDevId = dataDevAccount.getId();
            if (dataDevId == 1) {
                throw new SocialBusinessException("数据重复写入错误");
            }
            //仅仅是个校验，防止重复写入 ， dataSocialuniId都不空才能查，dataSociuni为空，则肯定thirdId为空
            if (StringUtils.isNotEmpty(thirdId)) {
                //如果已经存在此动态，则无需重复添加，直接返回
                UniThirdContentDO uniThirdContentDO = uniThirdContentRepository.findByDevIdAndContentTypeAndThirdId(dataDevId, contentType, thirdId);
                if (uniThirdContentDO != null) {
                    return null;
                }
            }
        }
        //不为空
        SocialuniUidRO socialuniUidRO = domain.apply(contentAddQO);


        //给中心设置开发者秘钥，在应用启动时，判断是否设置了中心服务器，

        devAccountRepository.findAllByStatusAndApiUrlNotNullAndApiSecretKeyNotNull(CommonStatus.enable);


        //如果自身为中心
        if (DevAccountUtils.notCenterServer()) {
            //如果无后台模式会为空
            UniThirdContentDO uniThirdContentDO;
            if (StringUtils.isEmpty(thirdId)) {
                uniThirdContentDO = new UniThirdContentDO(devId, contentType, thirdId, socialuniUidRO.getId());
            } else {
                uniThirdContentDO = new UniThirdContentDO(devId, contentType, socialuniUidRO.getId(), socialuniUidRO.getId());
            }
            uniThirdContentRepository.save(uniThirdContentDO);
        }
        //如果配置了中心
        if (SocialAppConfig.hasCenterServer()) {
            ResultRO<CenterMineUserDetailRO> resultRO = socialuniUserAPI.queryThirdUser();
            CenterMineUserDetailRO centerMineUserDetailRO = resultRO.getData();
            if (centerMineUserDetailRO == null) {
                UserDO mineUser = CenterUserUtil.getMineUser();
                //生成登录类
                SocialProviderLoginQO socialProviderLoginQO = new SocialProviderLoginQO();
                socialProviderLoginQO.setNickName(mineUser.getNickname());
                socialProviderLoginQO.setAvatarUrl(mineUser.getAvatar());
                socialProviderLoginQO.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
                socialProviderLoginQO.setBirthday(mineUser.getBirthday());
                socialProviderLoginQO.setCity(mineUser.getCity());
                socialProviderLoginQO.setUnionId(mineUser.getId().toString());

                socialProviderLoginQO.setProvider(RequestUtil.getProvider());
                socialProviderLoginQO.setPlatform(RequestUtil.getPlatform());
                socialProviderLoginQO.setSystem(RequestUtil.getSystem());

                socialuniUserAPI.registryUser(socialProviderLoginQO);
            }
            contentAddQO.setId(socialuniUidRO.getId());
//            callApi.apply(contentAddQO);
        } else {

        }
        return socialuniUidRO;
    }
}
