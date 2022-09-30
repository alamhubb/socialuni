package com.socialuni.social.sdk.logic.manage;


import com.socialuni.social.tance.repository.DevAccountProviderRepository;
import com.socialuni.social.tance.sdk.api.DevAccountApi;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.entity.DevAccountProviderDO;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.sdk.model.QO.user.OAuthUserInfoQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DevAccountManage {
    @Resource
    DevAccountProviderRepository devAccountProviderRepository;
    @Resource
    DevAccountApi devAccountApi;

    public DevAccountModel checkApplyAuthQO(OAuthUserInfoQO authVO) {
        DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findOneByAppIdAndMpType(authVO.getAppId(), authVO.getMpType());
//        Optional<DevAccountProviderDO> devAccountProviderDOOptional = devAccountProviderRepository.findFirstByAppIdAndMpTypeAndStatus(authVO.getAppId(), authVO.getMpType(), CommonStatus.enable);
        if (devAccountProviderDO == null) {
            throw new SocialBusinessException("应用未注册社交联盟开发者");
        }
        //开发者账号正确
        DevAccountModel threeDevDO = devAccountApi.findOneById(devAccountProviderDO.getDevId());
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
