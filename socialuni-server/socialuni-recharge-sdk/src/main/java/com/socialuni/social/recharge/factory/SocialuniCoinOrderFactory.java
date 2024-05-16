package com.socialuni.social.recharge.factory;

import com.socialuni.social.recharge.dao.DO.SocialuniCoinOrderDO;
import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;

public class SocialuniCoinOrderFactory {
    //充值成功创建订单
//    public static SocialuniCoinOrderDO createCoinOrderDOByOrderType(SocialuniUserCoinDo socialuniUserCoinDo, Integer rechargeNum, String orderType, Integer relationOrderId) {
//        SocialuniCoinOrderDO socialuniCoinOrderDO = new SocialuniCoinOrderDO(socialuniUserCoinDo.getUserId(), rechargeNum, orderType, socialuniUserCoinDo.getCoin());
//        if (SocialuniCoinOrderType.recharge.equals(orderType)) {
//            socialuniCoinOrderDO.setRechargeOrderId(relationOrderId);
//        } else if (SocialuniCoinOrderType.expense.equals(orderType)) {
//            socialuniCoinOrderDO.setUserContactId(relationOrderId);
//        }
//        return socialuniCoinOrderDO;
//    }
}
