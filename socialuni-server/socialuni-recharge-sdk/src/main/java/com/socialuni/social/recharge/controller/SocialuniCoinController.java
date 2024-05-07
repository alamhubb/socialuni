package com.socialuni.social.recharge.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.recharge.model.SocialuniCoinInfoRO;
import com.socialuni.social.recharge.model.SocialuniCoinPayRO;
import com.socialuni.social.recharge.model.SocialuniPayCoinQO;
import com.socialuni.social.common.sdk.model.RO.SocialCircleRO;
import com.socialuni.social.recharge.api.SocialuniCoinAPI;
import com.socialuni.social.recharge.logic.domain.SocialuniPayCoinDomain;
import com.socialuni.social.common.sdk.model.QO.circle.SocialuniCoinOrdersQueryQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("socialuni/coin")
@RestController
public class SocialuniCoinController implements SocialuniCoinAPI {
    @Resource
    SocialuniPayCoinDomain socialuniRechargeCoinDomain;

    @Override
    public ResultRO<List<SocialCircleRO>> queryCoinOrders(SocialuniCoinOrdersQueryQO socialuniCoinOrdersQueryQO) {
        return null;
    }

    @Override
    public ResultRO<SocialuniCoinPayRO> payCoin(SocialuniPayCoinQO socialuniRechargeCoinQO) {
        SocialuniCoinPayRO socialuniCoinPayRO = socialuniRechargeCoinDomain.payCoin(socialuniRechargeCoinQO);
        return ResultRO.success(socialuniCoinPayRO);
    }

    @Override
    public ResultRO<SocialuniCoinInfoRO> getUserCoinInfo() {
        return ResultRO.success(socialuniRechargeCoinDomain.getUserCoinInfo());
    }
}
