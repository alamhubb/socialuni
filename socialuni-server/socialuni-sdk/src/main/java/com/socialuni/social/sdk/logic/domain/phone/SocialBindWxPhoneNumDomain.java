package com.socialuni.social.sdk.logic.domain.phone;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.common.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.content.constant.SocialuniSupportProviderType;
import com.socialuni.social.user.sdk.logic.entity.SocialUserPhoneEntity;
import com.socialuni.social.user.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.social.common.sdk.platform.WxDecode;
import com.socialuni.social.common.sdk.platform.WxPhoneNumRO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.UniUnionIdRO;
import com.socialuni.social.user.sdk.utils.UniProviderUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    public String bindWxPhoneNum(@Valid SocialBindWxPhoneNumQO bindPhoneQO, SocialuniUserDo mineUser) {
        //校验各个参数
        SocialProviderLoginQO socialProviderLoginQO = new SocialProviderLoginQO();
        socialProviderLoginQO.setProvider(SocialuniSupportProviderType.wx);
//        socialProviderLoginQO.setPlatform(PlatformType.mp);
        socialProviderLoginQO.setCode(bindPhoneQO.getCode());

        UniUnionIdRO loginResult = UniProviderUtil.getUnionIdRO(socialProviderLoginQO);
        String sessionKey = loginResult.getSession_key();

        socialUserAccountManage.updateSessionKey(mineUser.getUnionId(), SocialuniSupportProviderType.wx, loginResult.getSession_key());
        WxPhoneNumRO phoneNumVO;
        try {
            String phoneJson = WxDecode.decrypt(sessionKey, bindPhoneQO.getEncryptedData(), bindPhoneQO.getIv());
            phoneNumVO = JsonUtil.objectMapper.readValue(phoneJson, WxPhoneNumRO.class);
        } catch (Exception e) {
            throw new SocialBusinessException("再点击一次绑定按钮即可完成绑定");
        }
        mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, phoneNumVO.getCountryCode(), phoneNumVO.getPurePhoneNumber());
        //根据用户得到返回详情
//        SocialuniMineUserDetailRO mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
        //返回真实手机号
//        mineUserDetailRO.setPhoneNum(phoneNumVO.getPurePhoneNumber());
        return phoneNumVO.getPurePhoneNumber();
    }
}
