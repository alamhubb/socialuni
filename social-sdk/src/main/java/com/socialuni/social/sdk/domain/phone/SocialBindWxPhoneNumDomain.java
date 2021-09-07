package com.socialuni.social.sdk.domain.phone;

import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.sdk.constant.platform.UniappProviderType;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.manage.SocialUserAccountManage;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.platform.WxDecode;
import com.socialuni.social.sdk.platform.WxPhoneNumRO;
import com.socialuni.social.sdk.utils.UniProviderUtil;
import com.socialuni.social.utils.JsonUtil;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Valid;

@Component
@Slf4j
public class SocialBindWxPhoneNumDomain {
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;
    @Resource
    private SocialUserAccountManage socialUserAccountManage;

    //微信绑定手机号方法
    public SocialMineUserDetailRO bindWxPhoneNum(@Valid SocialBindWxPhoneNumQO bindPhoneQO, UserDO mineUser) {
        //校验各个参数
        SocialProviderLoginQO socialProviderLoginQO = new SocialProviderLoginQO();
        socialProviderLoginQO.setProvider(UniappProviderType.wx);
        socialProviderLoginQO.setPlatform(PlatformType.mp);
        socialProviderLoginQO.setCode(bindPhoneQO.getCode());

        UniUnionIdRO loginResult = UniProviderUtil.getUnionIdRO(socialProviderLoginQO);
        String sessionKey = loginResult.getSession_key();

        socialUserAccountManage.updateSessionKey(UniappProviderType.wx, loginResult.getSession_key(), mineUser.getId());
        WxPhoneNumRO phoneNumVO;
        try {
            String phoneJson = WxDecode.decrypt(sessionKey, bindPhoneQO.getEncryptedData(), bindPhoneQO.getIv());
            phoneNumVO = JsonUtil.objectMapper.readValue(phoneJson, WxPhoneNumRO.class);
        } catch (Exception e) {
            throw new SocialBusinessException("再点击一次绑定按钮即可完成绑定");
        }
        mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, phoneNumVO.getCountryCode(), phoneNumVO.getPurePhoneNumber());
        //根据用户得到返回详情
        SocialMineUserDetailRO mineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        //返回真实手机号
        mineUserDetailRO.setPhoneNum(phoneNumVO.getPurePhoneNumber());
        return mineUserDetailRO;
    }
}
