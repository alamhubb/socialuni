package com.socialuni.sdk.logic.check;

import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.sdk.dao.DO.tag.SocialuniTagTypeDO;
import com.socialuni.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.community.SocialuniTagTypeRepository;
import com.socialuni.sdk.dao.store.SocialuniCircleRedis;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialNotLoginException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
