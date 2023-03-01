package com.socialuni.social.sdk.feignAPI.business;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.community.sdk.model.SocialCircleRO;
import com.socialuni.social.sdk.model.QO.circle.SocialuniCoinOrdersQueryQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/circle")
@FeignClient(name = "coin", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/coin")
@Tag(name = "商业模块/金币模块")
public interface SocialuniCoinAPI {
    @PostMapping("queryCoinOrders")
    @Operation(summary = "查询用户的金币历史")
    ResultRO<List<SocialCircleRO>> queryCirclesByCircleType(@RequestBody @Valid SocialuniCoinOrdersQueryQO socialuniCoinOrdersQueryQO);

}

