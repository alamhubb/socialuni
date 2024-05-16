package com.socialuni.social.recharge.logic.entity;

import com.socialuni.social.recharge.logic.manage.SocialuniCoinOrderManage;
import com.socialuni.social.recharge.logic.manage.SocialuniUserCoinManage;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniCreateCoinOrderEneity {
    @Resource
    SocialuniUserCoinManage socialuniUserCoinManage;
    @Resource
    SocialuniCoinOrderManage socialuniCoinOrderManage;

    public void createCoinOrderByOrderType(Integer userId, Integer coinNum, String orderType, Integer relationOrderId) {
        SocialuniUserCoinDo socialuniUserCoinDo = socialuniUserCoinManage.updateUserCoin(userId, coinNum);

        socialuniCoinOrderManage.createCoinOrder(socialuniUserCoinDo, coinNum, orderType, relationOrderId);
    }
}
