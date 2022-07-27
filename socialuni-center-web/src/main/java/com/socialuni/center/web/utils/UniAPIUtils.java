package com.socialuni.center.web.utils;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.SocialuniUidRO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.model.DO.UniThirdContentDO;
import com.socialuni.center.web.repository.UniThirdContentRepository;
import com.socialuni.cloud.config.SocialAppEnv;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.model.model.QO.ContentAddQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;


@Component
public class UniAPIUtils {
    static SocialuniUserAPI socialuniUserAPI;
    static UniThirdContentRepository uniThirdContentRepository;

    @Resource
    public void setUniThirdContentRepository(UniThirdContentRepository uniThirdContentRepository) {
        UniThirdContentUtils.uniThirdContentRepository = uniThirdContentRepository;
    }

    @Resource
    public void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        UniAPIUtils.socialuniUserAPI = socialuniUserAPI;
    }

    //list转换，TO类List转为RO类List
    public static <QO extends ContentAddQO, RO extends SocialuniUidRO> SocialuniUidRO callUniAPI(String contentType, Function<QO, SocialuniUidRO> domain, Function<QO, ResultRO<RO>> callApi, QO contentAddQO) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        Integer devId = devAccountDO.getId();
        if (StringUtils.isNotEmpty(contentAddQO.getId())) {
            if (devAccountDO.getId() == 1) {
                throw new SocialSystemException("默认开发者不该进入校验内容是否重复逻辑");
            }
            //如果已经存在此动态，则无需重复添加，直接返回
            UniThirdContentDO uniThirdContentDO = uniThirdContentRepository.findByDevIdAndContentTypeAndThirdId(devId, contentType, contentAddQO.getId());
            if (uniThirdContentDO != null) {
                return null;
            }
        }
        SocialuniUidRO socialuniUidRO = domain.apply(contentAddQO);
        //如果自身为中心
        if (StringUtils.isNotEmpty(contentAddQO.getId())) {
            UniThirdContentDO uniThirdContentDO = new UniThirdContentDO(devId, contentType, contentAddQO.getId(), socialuniUidRO.getId());
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
            callApi.apply(contentAddQO);
        }
        return socialuniUidRO;
    }
}
