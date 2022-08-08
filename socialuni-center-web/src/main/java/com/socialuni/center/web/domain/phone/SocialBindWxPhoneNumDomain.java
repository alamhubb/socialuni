package com.socialuni.center.web.domain.phone;

import com.socialuni.center.web.constant.platform.PlatformType;
import com.socialuni.center.web.constant.platform.UniappProviderType;
import com.socialuni.center.web.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.center.web.manage.SocialUserAccountManage;
import com.socialuni.center.web.model.UniUnionIdRO;
import com.socialuni.center.web.platform.WxDecode;
import com.socialuni.center.web.platform.WxPhoneNumRO;
import com.socialuni.center.web.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.center.web.utils.UniProviderUtil;
import com.socialuni.social.web.sdk.utils.JsonUtil;
import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
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
