package com.socialuni.social.sdk.logic.check;

import com.socialuni.social.common.sdk.constant.SocialSystemConst;
import com.socialuni.social.common.sdk.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.facade.ConfigFacade;
import com.socialuni.social.user.sdk.model.SocialUserPhoneModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import org.springframework.stereotype.Service;

@Service
public class SocialuniUserCheck {

    //校验用户是否设置了手机号
    public static void checkUserBindPhoneNumAndStatusNoEnable(SocialuniUserModel mineUser) {
        if (mineUser == null) {
            throw new SocialNotLoginException();
        }
        SocialUserPhoneModel userPhoneNum = SocialuniUserUtil.getUserPhoneNumDO(mineUser.getUnionId());
        //如果不为系统管理员，只有管理员才能评论置顶内容
        //未绑定手机号，不能发表动态，正常用户应该无法访问，应为突破了内容，或者逻辑有问题
        if (userPhoneNum == null) {
            throw new SocialParamsException(ErrorMsg.bindPhoneNumCan);
        }
        //如果用户状态不为可用
        if (!UserStatus.enable.equals(mineUser.getStatus())) {
//            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
            ConfigFacade.throwBusinessException(ErrorMsg.userMaybeViolation, SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT);
        }
    }
}
