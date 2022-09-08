package com.socialuni.sdk.logic.domain.phone;

import com.socialuni.sdk.constant.platform.PlatformType;
import com.socialuni.sdk.constant.platform.UniappProviderType;
import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.platform.WxDecode;
import com.socialuni.sdk.platform.WxPhoneNumRO;
import com.socialuni.sdk.logic.entity.user.SocialUserPhoneEntity;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.utils.UniProviderUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.web.sdk.utils.JsonUtil;
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
    public SocialMineUserDetailRO bindWxPhoneNum(@Valid SocialBindWxPhoneNumQO bindPhoneQO, SocialUserDO mineUser) {
        //校验各个参数
        SocialProviderLoginQO socialProviderLoginQO = new SocialProviderLoginQO();
        socialProviderLoginQO.setProvider(UniappProviderType.wx);
        socialProviderLoginQO.setPlatform(PlatformType.mp);
        socialProviderLoginQO.setCode(bindPhoneQO.getCode());

        UniUnionIdRO loginResult = UniProviderUtil.getUnionIdRO(socialProviderLoginQO);
        String sessionKey = loginResult.getSession_key();

        socialUserAccountManage.updateSessionKey(UniappProviderType.wx, loginResult.getSession_key(), mineUser.getUnionId());
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
