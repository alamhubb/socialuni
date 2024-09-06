package com.socialuni.social.sdk.logic.manage;


import com.socialuni.social.tance.dev.api.DevAccountProviderInterface;
import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.model.DevAccountProviderModler;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.user.sdk.model.QO.OAuthUserInfoQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DevAccountManage {
    @Resource
    DevAccountProviderInterface devAccountProviderApi;
    @Resource
    DevAccountInterface devAccountApi;

    public DevAccountDo checkApplyAuthQO(OAuthUserInfoQO authVO) {
        DevAccountProviderModler devAccountProviderModler = devAccountProviderApi.findOneByAppIdAndMpType(authVO.getAppId(), authVO.getMpType());
//        Optional<DevAccountProviderDO> devAccountProviderDOOptional = devAccountProviderRepository.findFirstByAppIdAndMpTypeAndStatus(authVO.getAppId(), authVO.getMpType(), CommonStatus.enable);
        if (devAccountProviderModler == null) {
            throw new SocialBusinessException("应用未注册社交联盟开发者");
        }
        //开发者账号正确
        DevAccountDo threeDevDO = devAccountApi.findFirstById(devAccountProviderModler.getDevId());
        //还能知道密钥是否被盗用
        //校验密钥是否正确
        /*String threeSecretKey = authVO.getThreeSecretKey();
        if (StringUtils.isNotEmpty(threeSecretKey)) {
            if (!threeSecretKey.equals(threeDevDO.getSecretKey())) {
                throw new SocialBusinessException("不存在的开发者");
            }
        }*/
        return threeDevDO;
    }
}
