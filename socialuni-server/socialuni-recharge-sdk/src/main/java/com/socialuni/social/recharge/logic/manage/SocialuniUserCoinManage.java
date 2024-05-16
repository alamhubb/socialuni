package com.socialuni.social.recharge.logic.manage;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserSocialCoinDOUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SocialuniUserCoinManage {

    public SocialuniUserCoinDo updateUserCoin(Integer userId, Integer coinNum) {
        SocialuniUserCoinDo userCoinDo = SocialuniUserSocialCoinDOUtil.getOrCreate(userId);

        userCoinDo.setCoin(userCoinDo.getCoin() + coinNum);

        if (userCoinDo.getCoin() < 0) {
            throw new SocialParamsException("金币不足");
        }

        userCoinDo.setUpdateTime(new Date());

        //更新用户的coin数量
        userCoinDo = SocialuniUserSocialCoinDOUtil.update(userCoinDo);
        return userCoinDo;
    }
}
