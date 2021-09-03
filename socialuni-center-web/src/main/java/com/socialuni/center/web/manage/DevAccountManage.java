package com.socialuni.center.web.manage;


import com.socialuni.center.sdk.repository.DevAccountProviderRepository;
import com.socialuni.center.sdk.repository.DevAccountRepository;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.entity.model.DevAccountProviderDO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.model.model.QO.user.OAuthUserInfoQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DevAccountManage {
    @Resource
    DevAccountProviderRepository devAccountProviderRepository;
    @Resource
    DevAccountRepository devAccountRepository;

    public DevAccountDO checkApplyAuthQO(OAuthUserInfoQO authVO) {
        DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findFirstByAppIdAndMpType(authVO.getAppId(), authVO.getMpType());
//        Optional<DevAccountProviderDO> devAccountProviderDOOptional = devAccountProviderRepository.findFirstByAppIdAndMpTypeAndStatus(authVO.getAppId(), authVO.getMpType(), CommonStatus.enable);
        if (devAccountProviderDO == null) {
            throw new SocialBusinessException("应用未注册社交联盟开发者");
        }
        //开发者账号正确
        DevAccountDO threeDevDO = devAccountRepository.findFirstById(devAccountProviderDO.getDevId());
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
