package com.socialuni.center.web.manage;


import com.socialuni.center.web.model.DO.DevAccountDO;
import com.socialuni.center.web.model.DO.DevAccountProviderDO;
import com.socialuni.center.web.repository.DevAccountProviderRepository;
import com.socialuni.center.web.repository.DevAccountRepository;
import com.socialuni.constant.CommonStatus;
import com.socialuni.sdk.exception.SocialBusinessException;
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
        Optional<DevAccountProviderDO> devAccountProviderDOOptional = devAccountProviderRepository.findFirstByAppIdAndMpTypeAndStatus(authVO.getAppId(), authVO.getMpType(), CommonStatus.enable);
        if (!devAccountProviderDOOptional.isPresent()) {
            throw new SocialBusinessException("应用未注册社交联盟开发者");
        }

        DevAccountProviderDO devAccountProviderDO = devAccountProviderDOOptional.get();

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
