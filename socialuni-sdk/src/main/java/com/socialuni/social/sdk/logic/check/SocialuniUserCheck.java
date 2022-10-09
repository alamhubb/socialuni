package com.socialuni.social.sdk.logic.check;

import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.user.sdk.model.SocialUserPhoneDO;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.common.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.springframework.stereotype.Service;

@Service
public class SocialuniUserCheck {

    //校验用户是否设置了手机号
    public static void checkUserBindPhoneNumAndStatusNoEnable(SocialuniUserDO mineUser) {
        if (mineUser == null) {
            throw new SocialNotLoginException();
        }
        SocialUserPhoneDO userPhoneNum = SocialuniUserUtil.getUserPhoneNumDO(mineUser.getUnionId());
        //如果不为系统管理员，只有管理员才能评论置顶内容
        //未绑定手机号，不能发表动态，正常用户应该无法访问，应为突破了内容，或者逻辑有问题
        if (userPhoneNum == null) {
            throw new SocialParamsException(ErrorMsg.bindPhoneNumCan);
        }
        //如果用户状态不为可用
        if (!UserStatus.enable.equals(mineUser.getStatus())) {
            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
        }
    }
}
