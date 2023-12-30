package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.community.sdk.model.SocialCircleRO;
import com.socialuni.social.sdk.feignAPI.business.SocialuniCoinAPI;
import com.socialuni.social.sdk.logic.domain.business.SocialuniPayCoinDomain;
import com.socialuni.social.sdk.model.QO.business.SocialuniCoinPayRO;
import com.socialuni.social.sdk.model.QO.business.SocialuniPayCoinQO;
import com.socialuni.social.sdk.model.QO.circle.SocialuniCoinOrdersQueryQO;
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
}
