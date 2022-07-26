package com.socialuni.center.web.utils;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.SocialuniUidRO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.ContentAddQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;


@Component
public class UniAPIUtils {
    static SocialuniUserAPI socialuniUserAPI;

    @Resource
    public void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        UniAPIUtils.socialuniUserAPI = socialuniUserAPI;
    }

    //list转换，TO类List转为RO类List
    public static <QO extends ContentAddQO, RO extends SocialuniUidRO> void callUniAPI(SocialuniUidRO appRO, Function<QO, ResultRO<RO>> function, QO postQO) {
        ResultRO<CenterMineUserDetailRO> resultRO = socialuniUserAPI.getMineUser();
        CenterMineUserDetailRO centerMineUserDetailRO = resultRO.getData();
        if (centerMineUserDetailRO == null) {
            UserDO mineUser = SocialUserUtil.getMineUserNotNull();

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
        postQO.setId(appRO.getId());
        function.apply(postQO);
    }
}
