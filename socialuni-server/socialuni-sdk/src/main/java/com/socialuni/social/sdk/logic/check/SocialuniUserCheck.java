package com.socialuni.social.sdk.logic.check;

import com.socialuni.social.common.api.constant.SocialSystemConst;
import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.tance.config.ErrorMsg;
import com.socialuni.social.user.sdk.constant.SocialuniUserStatus;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.facade.ConfigFacade;
import com.socialuni.social.user.sdk.model.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

@Service
public class SocialuniUserCheck {

    //校验用户是否设置了手机号
    public static void checkUserBindPhoneNumAndStatusNoEnable(SocialuniUserDo mineUser) {
        if (mineUser == null) {
            throw new SocialNotLoginException();
        }
        SocialUserPhoneDo userPhoneNum = SocialuniUserUtil.getUserPhoneNumDO(mineUser.getUnionId());
        //如果不为系统管理员，只有管理员才能评论置顶内容
        //未绑定手机号，不能发表动态，正常用户应该无法访问，应为突破了内容，或者逻辑有问题
        if (userPhoneNum == null) {
            throw new SocialParamsException(ErrorMsg.bindPhoneNumCan);
        }
        //如果用户状态不为可用
        if (!SocialuniUserStatus.enable.equals(mineUser.getStatus())) {
//            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
            ConfigFacade.throwBusinessException(ErrorMsg.userMaybeViolation, SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT);
        }
    }
}