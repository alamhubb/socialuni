package com.socialuni.social.sdk.dao.factory;

import com.socialuni.social.sdk.constant.shell.SocialuniCoinOrderType;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniCoinOrderDO;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;

public class SocialuniCoinOrderFactory {

    //充值成功创建订单
    public static SocialuniCoinOrderDO createCoinOrderDOByRechargeSuccess(SocialuniUserCoinDo socialuniUserCoinDo, Integer rechargeNum, Integer rechargeOrderId) {
        SocialuniCoinOrderDO socialuniCoinOrderDO = new SocialuniCoinOrderDO(socialuniUserCoinDo.getUserId(), rechargeNum, SocialuniCoinOrderType.recharge, socialuniUserCoinDo.getCoin());
        socialuniCoinOrderDO.setRechargeOrderId(rechargeOrderId);
        return socialuniCoinOrderDO;
    }

    public static SocialuniCoinOrderDO createCoinOrderDOByContactInfoSuccess(SocialuniUserCoinDo socialuniUserCoinDo, Integer expanseNum, Integer userContactId) {
        SocialuniCoinOrderDO socialuniCoinOrderDO = new SocialuniCoinOrderDO(socialuniUserCoinDo.getUserId(), expanseNum, SocialuniCoinOrderType.expense, socialuniUserCoinDo.getCoin());
        socialuniCoinOrderDO.setUserContactId(userContactId);
        return socialuniCoinOrderDO;
    }
}
